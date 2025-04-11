import { Component } from '@angular/core';
import { Domanda, Risposta, RispostaUtente, UtenteTest } from '../../model';
import { DomandaService } from '../../service/domanda.service';
import { QuestionarioService } from '../../service/questionario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-questionario-utente',
  templateUrl: './questionario-utente.component.html',
  styleUrls: ['./questionario-utente.component.css']
})
export class QuestionarioUtenteComponent {

  domande: Domanda[] = []
  risposta: Risposta = new Risposta()
  error1!: string
  punteggio!: number

  constructor(
    private domandaService: DomandaService, 
    private questionarioService: QuestionarioService,
    private router: Router
    ) {}

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
        this.error1 = "Error in Db!"
        console.log(err)
      },
      complete: () => {
        console.log("Request completed")
      }
    })
  }

  salvaRisposte(domanda: Domanda): void {
    // Aggiorna o aggiungi la domanda nel sessionStorage
    const savedDomande = sessionStorage.getItem('domande');
    let domandeArray: Domanda[] = savedDomande ? JSON.parse(savedDomande) : [];
    
    // Trova e aggiorna la domanda, oppure aggiungila se non esiste
    const index = domandeArray.findIndex(d => d.domandaId === domanda.domandaId);
    if (index !== -1) {
      // Se la domanda esiste, aggiorna la sua risposta
      console.log(`Domanda con ID ${domanda.domandaId} aggiornata`);
      domandeArray[index] = domanda;
    } else {
      // Se la domanda è nuova, aggiungila all'array
      console.log(`Domanda con ID ${domanda.domandaId} aggiunta`);
      domandeArray.push(domanda);
    }
    // Salva l'array aggiornato nel sessionStorage
    sessionStorage.setItem('domande', JSON.stringify(domandeArray));
    console.log('Domande aggiornate nel sessionStorage:', domandeArray);
  }

  inviaRisposte(): void {
    const userId = this.questionarioService.getUserIdFromToken();
    console.log("UserId:", userId);
    if (!userId) {
      console.error("Impossibile inviare le risposte senza un ID utente valido", userId);
      return;
    }

    const utenteTestId = this.questionarioService.getUtenteTestId();
    if (!utenteTestId) {
      console.error("UtenteTestId non trovato, il test non può essere aggiornato.");
      return;
    }
  
    const rispostaUtenteString = sessionStorage.getItem("domande");
  
    if (!rispostaUtenteString) {
      console.log("Nulla trovato");
      return;
    }
    
    const rispostaUtente = JSON.parse(rispostaUtenteString).filter((domanda: Domanda) => domanda.selezione !== undefined);
  
    // Verifica che i dati inviati siano corretti
    const risposteInviate = rispostaUtente.map((domanda: Domanda) => ({
      rispostaId: domanda.selezione || null,
      domandaId: domanda.domandaId,
      utenteTestIdId: utenteTestId,
      punteggioOttenuto: 0
    }));


    this.questionarioService.aggiorna(utenteTestId, risposteInviate).subscribe({
    next: (data) => {
      console.log("Risposte: ", risposteInviate)
      console.log("Test inviato con successo", data);
      sessionStorage.removeItem("domande");
      sessionStorage.removeItem("utenteTestId")
      sessionStorage.setItem("punteggio", data)
      this.router.navigateByUrl("home");
      }, 
      error: (err) => {
      console.log("Risposte: ", risposteInviate)
      console.error("Errore durante l'invio delle risposte", err);
      sessionStorage.removeItem("domande");
      sessionStorage.removeItem("utenteTestId");
      this.router.navigateByUrl("home")
      }
    });
  }  
}


