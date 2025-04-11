import { Component } from '@angular/core';
import { Utente } from 'src/app/model';
import { UtenteService } from 'src/app/service/utente.service';

@Component({
  selector: 'app-lista-utenti',
  templateUrl: './lista-utenti.component.html',
  styleUrls: ['./lista-utenti.component.css']
})
export class ListaUtentiComponent {
  
  utente: Utente[] = []
  constructor(private utenteService: UtenteService) {}

  ngOnInit() {
    this.getUtenti();
  }

  getUtenti(): void {
    this.utenteService.getUtenti().subscribe({
      next: (utenti: Utente[]) => {
        this.utente = utenti
        console.log(utenti)
      },
      error: (err) => {
        console.log("Lista utenti non caricata", err)
      }
    })
  }
}
