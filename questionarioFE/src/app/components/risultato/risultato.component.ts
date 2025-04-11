import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-risultato',
  templateUrl: './risultato.component.html',
  styleUrls: ['./risultato.component.css']
})
export class RisultatoComponent {

  constructor(private router: Router) {}

  punteggioFinale = sessionStorage.getItem("punteggio")

  onContinue() {
    this.router.navigateByUrl("home")
    sessionStorage.removeItem("punteggio")
  }

}
