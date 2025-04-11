package it.objectmethod.questionario2024;

import lombok.Getter;

@Getter
public enum Constants {

    /*
     * Variabili per DomandaService
     */
    QUESTION_NOT_FOUND("Domanda non trovata"),
    QUESTION_DELETED("Domanda eliminata con successo!"),

    /*
     * Variabili per TestService
     */
    REGISTRAZIONE_EFFETTUATA("Registrazione effettuata con successo"),
    ALREADY_SIGNIN("Utente già registrato"),
    /*
     * Variabili per AuthService
     */
    TEST_NOT_FOUND("Test non trovato"),
    TEST_DELETED("Test eliminato con successo"),
    /*
     * Variabili per AuthService
     */
    USER_NOT_FOUND("Impossibile trovare l'utente desiderato"),

    SIGNIN_ERROR("Errore durante la registrazione dell'utente"),

    /*
     * Variabili per UtenteService
     */
    USER_DELETED("Utente eliminato con successo"),
    USER_TEST_NOT_FOUND("UtenteTest non trovato");

    private final String message;

    Constants(final String message) {
        this.message = message;
    }
}
