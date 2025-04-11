import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Domanda } from '../model';

@Injectable({
  providedIn: 'root'
})
export class DomandaService {

  private apiUrl = 'http://localhost:8080/api/domande'

  constructor(private http: HttpClient) { }

  getDomande(): Observable<Domanda[]> {
    return this.http.get<Domanda[]>(`${this.apiUrl}`)
  }

  postDomanda(domanda: Domanda): Observable<Domanda> {
    return this.http.post<Domanda>(`${this.apiUrl}`, domanda)
  }

  updateDomanda(domanda: Domanda): Observable<Domanda> {
    return this.http.put<Domanda>(`${this.apiUrl}/${domanda.domandaId}`, domanda)
  }

  deleteDomanda(domanda: Domanda): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/${domanda.domandaId}`, {responseType: "text" as "json"})
  }

}
