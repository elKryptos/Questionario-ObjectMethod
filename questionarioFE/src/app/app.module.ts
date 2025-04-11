import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DomandeComponent } from './components/domande/domande.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { CreaDomandaComponent } from './components/crea-domanda/crea-domanda.component';
import { FormsModule } from '@angular/forms';
import { RegistrazioneComponent } from './components/registrazione/registrazione.component';
import { Tokeninterceptor } from './interceptor/tokeninterceptor';
import { HomeComponent } from './components/home/home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { QuestionarioUtenteComponent } from './components/questionario-utente/questionario-utente.component';
import { ListaUtentiComponent } from './components/lista-utenti/lista-utenti.component';
import { RisultatoComponent } from './components/risultato/risultato.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DomandeComponent,
    CreaDomandaComponent,
    RegistrazioneComponent,
    HomeComponent,
    QuestionarioUtenteComponent,
    ListaUtentiComponent,
    RisultatoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: Tokeninterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
