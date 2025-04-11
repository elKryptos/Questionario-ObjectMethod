import { Component } from '@angular/core';
import { Domanda, Risposta } from '../../model';
import { DomandaService } from '../../service/domanda.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-crea-domanda',
  templateUrl: './crea-domanda.component.html',
  styleUrls: ['./crea-domanda.component.css']
})
export class CreaDomandaComponent {

  domanda: Domanda = new Domanda();
  risposta!: Risposta
  message!: string
  error!: string

  constructor(private domandaService: DomandaService, private router: Router) {
    this.domanda.risposte = []
  }

  creaDomanda(): void {
    console.log(this.domanda)
    if(!this.domanda.testoDomanda){
      this.error = "Compila il form!"
      return
    }

    this.error = ""

    this.domandaService.postDomanda(this.domanda).subscribe({
      next: (response: Domanda) => {
        this.domanda = response
        console.log(response)
        this.router.navigateByUrl("domande")
      },
      error: (err) => {
        this.error = "Error in DB!"
        this.domanda = new Domanda()
        console.log("Error: " + err)
      },
      complete: () => {
        console.log("Request completed")
      }
    })
  }

  add(): void {
    this.domanda.risposte.push({
      rispostaId: this.domanda.domandaId,
      testoRisposta: '',
      corretta: false,
      domandaId: this.domanda.domandaId
    })
  }

  delete(index: number) {
    this.domanda.risposte.splice(index, 1)
    this.domanda.risposte = [...this.domanda.risposte]
  }
  

}
