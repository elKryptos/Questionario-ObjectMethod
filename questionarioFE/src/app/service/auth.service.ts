import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { Auth, Utente } from '../model';
import { ToastService } from './toast.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private toastService: ToastService) { }

  login(auth: Auth): Observable<{response: string}> {
    return this.http.post<{response: string}>("http://localhost:8080/auth/login", auth)
  }

  logout(): void {
    sessionStorage.removeItem("token")
    sessionStorage.removeItem("domande")
    sessionStorage.removeItem("punteggio")
    this.toastService.showToast("warning", "Logged out!")
  }

  isLoggedIn(): boolean {
    return !!sessionStorage.getItem("token")
  }

  getToken(): string | null {
    return sessionStorage.getItem("token")
  }

  setToken(token: string): void {
    sessionStorage.setItem("token", token)
  }

  registrazione(utente: Utente): Observable<string> {
    return this.http.post<string>("http://localhost:8080/auth/signin", utente, {responseType: 'text' as 'json'})
  }

  isAdmin(): boolean {
    const token = sessionStorage.getItem("token")
    if(token) {
      try{
        const decodedToken: any = jwtDecode(token)
        return decodedToken.isAdmin === "1"
      } catch (error) {
        console.log("Errore", error)
        return false
      }
    }
    return false
  }
  
}
