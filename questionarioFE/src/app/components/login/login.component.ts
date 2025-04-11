import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from '../../model';
import { AuthService } from '../../service/auth.service';
import { ToastService } from '../../service/toast.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  auth: Auth = {nome: "", email: "", password: ""}
  error!: string
  messaggioVisibile: boolean = false;

  constructor(private secure: AuthService, private router: Router, private toastService: ToastService) { }

  onLogin(): void {
    if(!this.auth.email || !this.auth.password) {
      this.toastService.showToast("warning", "Compila tutti i campi")
      this.messaggioVisibile = true
      setTimeout(() => {
        this.messaggioVisibile = false
      }, 2000)
    } else {
      this.secure.login(this.auth).subscribe({
        next: (data: {response: string}) => {
          if(data.response) {
          const token = data.response
          this.secure.setToken(token)
          this.router.navigateByUrl("home")
          this.toastService.showToast("success",  "Accesso Riuscito")
          } else {
            this.toastService.showToast("warning", "Riprova!")
          }
        },
        error: (err) => {
          if(err.status === 401) {
            this.error = "Non sei autorizzato!"
          } else {
            this.error = "Errore. Prova ancora!"
          }
        }
      })
    }
  }

  registrati(): void {
    this.router.navigateByUrl("registrazione")
  }

  recupera(): void {
    console.log("click recupera")
  }

}
