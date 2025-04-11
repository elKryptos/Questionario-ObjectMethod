import { Component } from '@angular/core';
import { Domanda } from '../../model';
import { DomandaService } from '../../service/domanda.service';

@Component({
  selector: 'app-domande',
  templateUrl: './domande.component.html',
  styleUrls: ['./domande.component.css']
})
export class DomandeComponent {
  message!: string
  domande: Domanda[] = []
  error!: string
  delete!: string
  messaggioVisibile: boolean = false
  domandaModificata: Domanda | null = null

  constructor(private domandaService: DomandaService) {}

  ngOnInit(): void {
    this.load()
  }

  load(): void {
    this.domandaService.getDomande().subscribe({
      next: (domande: Domanda[]) => {
       this.domande = domande
       console.log(domande)
      },
      error: (err) => {
        this.error = "Error in Db!"
        console.log(err)
      },
      complete: () => {
        console.log("Request completed")
      }
    })
  }

  elimina(domanda: Domanda): void {
    console.log("Eliminando domanda con ID: ", domanda.domandaId);
    console.log(domanda)
    this.domandaService.deleteDomanda(domanda).subscribe({
      next: (response: String) => {
        this.domande = this.domande.filter(item => item.domandaId !== domanda.domandaId);
        this.delete = response.toString()
        this.messaggioVisibile = true;  // Mostra il messaggio
        // Nascondi il messaggio dopo 2 secondi
        setTimeout(() => {
          this.messaggioVisibile = false;  // Nascondi il messaggio
        }, 2000)
      },
      error: (err) => {
        this.error = "Errore durante l'eliminazione!"
        console.log(err)
      },
      complete: () => {
        console.log("Request completed")
      }
    })
  } 

  confermaEliminazione(domanda: Domanda): void {
    domanda.inConferma = true; // Imposta lo stato di conferma
  }
  
  annullaEliminazione(domanda: Domanda): void {
    domanda.inConferma = false; // Reimposta lo stato
  }

  modifica(domanda: Domanda): void {}
  
  
}
