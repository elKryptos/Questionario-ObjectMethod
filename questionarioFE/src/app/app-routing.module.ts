import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DomandeComponent } from './components/domande/domande.component';
import { CreaDomandaComponent } from './components/crea-domanda/crea-domanda.component';
import { RegistrazioneComponent } from './components/registrazione/registrazione.component';
import { HomeComponent } from './components/home/home.component';
import { QuestionarioUtenteComponent } from './components/questionario-utente/questionario-utente.component';
import { Guard } from './guards/guard';
import { loggedInGuard } from './guards/logged-in.guard';
import { ListaUtentiComponent } from './components/lista-utenti/lista-utenti.component';
import { RisultatoComponent } from './components/risultato/risultato.component';

const routes: Routes = [
  { path: "home", component: HomeComponent },
  { path: "login", component: LoginComponent, canActivate: [loggedInGuard] },
  { path: "crea", component: CreaDomandaComponent, canActivate: [Guard] },
  { path: "registrazione", component: RegistrazioneComponent, canActivate: [loggedInGuard]},
  { path: "domande", component: DomandeComponent, canActivate: [Guard] },
  { path: "questionarioUtente", component: QuestionarioUtenteComponent },
  { path: "listaUtenti", component: ListaUtentiComponent },
  { path: "risultato", component: RisultatoComponent },
  { path: "**", component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
