import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Utente } from "../model";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UtenteService { 

    private apiUrl = "http://localhost:8080/api/utente"
    constructor(private http: HttpClient) { }

    getUtenti(): Observable<Utente[]>{
        return this.http.get<Utente[]>(`${this.apiUrl}`)
    }

}