import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Utente } from '../../model';
import { AuthService } from '../../service/auth.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css']
})
export class RegistrazioneComponent {

  utente: Utente = new Utente()
  message!: string
  error!: string
  messaggioVisibile: boolean = false
  
  constructor(private router: Router, private secure: AuthService, private location: Location) {}

  registrazione(): void {
    console.log(this.utente)
    if(!this.utente.nomeUtente || !this.utente.email || !this.utente.password || !this.utente.compleanno || !this.utente.indirizzo){
      this.error = "Compila il form!"
      this.messaggioVisibile = true
      setTimeout(() => {
        this.messaggioVisibile = false
      }, 2000 )
      return
    }

    this.secure.registrazione(this.utente).subscribe({
      next: (data) => {
        console.log(data);
        this.message = data
        this.utente = new Utente()
      },
      error: (err) => {
        console.error(err)
        this.error = "Errore in db, riprova!"
      }
    })
  }  

  indietro(): void {
    this.location.back()
  }
 
}
