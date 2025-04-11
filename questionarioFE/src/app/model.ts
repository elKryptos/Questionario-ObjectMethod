export class Auth {
    nome!: string
    email!: string
    password!: string
}

export class Utente {
    utenteId!: number
    nomeUtente!: string
    email!: string
    password!: string
    indirizzo!: string
    compleanno!: string
}

export class UtenteTest {
    utenteTestId!: number
    testId!: number
    utenteId!: number
    dataInizio!: Date
    dataFine!: Date
    punteggioTotale!: number
}

export class Test {
    testId!: number
    nomeTest!: string
    durataMinuti!: number
}

export class TestDomanda {
    testDomandaId!: number
    domandaId!: number
    testId!: number
}

export class Domanda {
    domandaId!: number
    testoDomanda!: string
    punteggio!: number
    risposte!: Risposta[]
    inConferma?: boolean
    selezione?: number;
}

export class Risposta {
    rispostaId!: number
    domandaId!: number
    testoRisposta!: string
    corretta!: boolean
}

export class RispostaUtente {
    rispostaUtenteId!: number
    rispostaId!: number
    domandaId!: number
    utenteTestId!: number
    punteggioOttenuto!: number
}

