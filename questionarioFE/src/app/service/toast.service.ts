import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ToastService {
  private toastSubject = new Subject<any>()
  public toastState = this.toastSubject.asObservable()

  private loading = new BehaviorSubject<boolean>(false)
  public readonly isLoading = this.loading.asObservable()

  constructor() { }

  showToast(type: 'success' | 'error' | 'warning', message: string) {
    this.toastSubject.next({type, message})
  }

  showSpinner() {
    this.loading.next(true)
  }

  hideSpinner() {
    this.loading.next(false)
  }
}
