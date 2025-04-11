import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RispostaUtente, UtenteTest } from '../model';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class QuestionarioService {

  utenteTestId!: number
  punteggio!: number
  private apiUrl = 'http://localhost:8080/api/utenteTest'

  constructor(private httpClient: HttpClient) { }

  getUtenteTestId(): number | null {
    const utenteTestId = sessionStorage.getItem("utenteTestId");
    console.log("UtenteTestId recuperato da sessionStorage:", utenteTestId);
    return utenteTestId ? Number(utenteTestId) : null;
  }

  getPunteggio(): number | null {
    const punteggio = sessionStorage.getItem("punteggio")
    return punteggio ? Number(punteggio) : null
  }
  
  creaTest(test: UtenteTest): Observable<UtenteTest> {
    return this.httpClient.post<UtenteTest>(`${this.apiUrl}`, test)
  }

  salvaECalcola(risposte: RispostaUtente[]): Observable<RispostaUtente[]> {
    return this.httpClient.post<RispostaUtente[]>(`${this.apiUrl}`, risposte)
  }

  aggiorna(utenteTestId: number, risposte: RispostaUtente[]): Observable<any> {
    return this.httpClient.put<any>(`${this.apiUrl}/${utenteTestId}/concludi`, risposte)
  }

  getUserIdFromToken(): number | null {
    const token = sessionStorage.getItem('token'); // O sessionStorage.getItem('token')
    if (!token) {
      console.error("Token non trovato");
      return null;
    }
  
    try {
      const decoded: any = jwtDecode(token); // Decodifica del token
      return decoded.utenteId || null; // Assumi che l'ID utente sia nel campo `userId`
    } catch (error) {
      console.error("Errore durante la decodifica del token", error);
      return null;
    }
  }
}
