import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { QuestionarioService } from '../../service/questionario.service';
import { UtenteTest } from '../../model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  test: UtenteTest = new UtenteTest()
  utenteTestId!: number

  constructor(private router: Router, private authService: AuthService, private questionarioService: QuestionarioService) {}

  startTest(): void {
    if(this.authService.isLoggedIn()) {
      if(!this.authService.isAdmin()){
        this.creaTest()
        this.router.navigateByUrl("questionarioUtente")
      } else {
        this.router.navigateByUrl("domande")
      }
    } else {
      this.router.navigateByUrl("login")
    }
  }
    
  creaTest() {
    this.questionarioService.creaTest(this.test).subscribe({
      next: (data: UtenteTest) => {
        this.test = data
        if(data && data.utenteTestId !== undefined && data.utenteTestId !== null) {
          sessionStorage.setItem("utenteTestId", data.utenteTestId.toString())
          console.log("Test creato: ", data)
        } else {
          console.error("utenteTestId non definito nella risposta del servizio.");
        }
      },
      error: (err) => {
        console.error("Errore durante la creazione del test:", err);
      }
    })
  }
}
