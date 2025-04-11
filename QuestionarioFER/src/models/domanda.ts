export interface DomandaProps {
  domanda: {
    domandaId: number;
    testoDomanda: string;
    punteggio: number;
    risposte: {
      rispostaId: number;
      testoRisposta: string;
      corretta: boolean;
      domandaId: number;
    }[];
  };
  onInputChange: (domandaId: number, rispostaId: string) => void;
}
