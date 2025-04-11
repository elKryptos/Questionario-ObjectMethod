import { ChangeDetectorRef, Component } from '@angular/core';
import { AuthService } from './service/auth.service';
import { Router } from '@angular/router';
import { ToastService } from './service/toast.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'questionarioFE';
  toasts: any[] = []; // Array per i toast dinamici
  menuOpen: boolean = false
  isLoading: boolean = false
  private subscriptions: Subscription = new Subscription()
  

  constructor(private secure: AuthService, private router: Router, private toastService: ToastService, private cdr: ChangeDetectorRef) {}

  ngOnInit(){
    this.toastService.toastState.subscribe((toast) => {
      this.toasts.push(toast)
      setTimeout(() => this.removeToast(toast), 2000)
    })

    const loadingSubscription =  this.toastService.isLoading.subscribe((loading: boolean) => {
      this.isLoading = loading
      this.cdr.detectChanges()
    })
    this.subscriptions.add(loadingSubscription)
    
  }

  get isLoggedIn(): boolean {
    return this.secure.isLoggedIn()
  }

  get isAdmin(): boolean {
    return this.secure.isAdmin()
  }

  onLogout(): void {
    this.secure.logout()
    this.router.navigateByUrl("home")
  }

  // Rimuove un toast specifico
  removeToast(toast: any) {
    const index = this.toasts.indexOf(toast);
    if (index !== -1) {
      this.toasts.splice(index, 1);
    }
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen
    const nav = document.querySelector('.navigation')
    if(this.menuOpen) {
      nav?.classList.add('active')
    } else {
      nav?.classList.remove('active')
    }
  }

  closeMenu() {
    this.menuOpen = false
  }
  
}
