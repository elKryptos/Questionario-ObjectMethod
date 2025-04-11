import React, { useState } from "react";
import { DashCircle, PlusCircle } from "react-bootstrap-icons";
import toast from "react-hot-toast";

export default function CreaDomanda() {
  const [testoDomanda, setTestoDomanda] = useState("");
  const [risposte, setRisposte] = useState([
    { testo: "", corretta: false },
    { testo: "", corretta: false },
  ]); // Due Risposte iniziali

  // Funzione per gestire il cambiamento del testo di una risposta
  const aggiornaRisposta = (index: number, valore: string) => {
    const nuoveRisposte = [...risposte];
    nuoveRisposte[index].testo = valore;
    setRisposte(nuoveRisposte);
  };

  // Funzione per gestire il cambiamento della checkbox corretta
  const selezionaCorretta = (index: number) => {
    const nuoveRisposte = [...risposte];
    nuoveRisposte[index].corretta = !nuoveRisposte[index].corretta;
    setRisposte(nuoveRisposte);
  };

  // Funzione per aggiungere un nuovo input di risposta
  const aggiungiRisposta = () => {
    setRisposte([...risposte, { testo: "", corretta: false }]);
  };

  // Funzione per rimuovere un input di risposta
  const rimuoviRisposta = (index: number) => {
    const nuoveRisposte = [...risposte];
    nuoveRisposte.splice(index, 1);
    setRisposte(nuoveRisposte);
  };

  // Funzione per inviare la domanda
  const inviaDomanda = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!testoDomanda.trim() || risposte.some((r) => !r.testo.trim())) {
      alert("Compila il testo della domanda e tutte le risposte.");
      return;
    }

    const nuovaDomanda = {
      testoDomanda,
      punteggio: 1,
      risposte: risposte.map((r) => ({
        testoRisposta: r.testo,
        corretta: r.corretta,
      })),
    };

    try {
      const response = await fetch("http://localhost:8080/api/domande", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(nuovaDomanda),
      });

      if (response.ok) {
        const data = await response.json();
        console.log("Risposta dal server:", data);
        toast.success("Domanda creata con successo!");
        // Resetta i campi
        setTestoDomanda("");
        setRisposte([
          { testo: "", corretta: false },
          { testo: "", corretta: false },
        ]);
      } else {
        const errorData = await response.json();
        console.error("Errore dal server:", errorData);
        toast.error("Errore durante la creazione della domanda.");
      }
    } catch (error) {
      console.error("Errore durante la creazione della domanda:", error);
      alert("Si è verificato un errore. Riprova.");
    }
  };

  return (
    <div className="container py-5">
      <div className="row d-flex justify-content-center">
        <div className="col-lg-8 col-md-10 col-sm-12 ">
          <div className="bg-dark text-white p-5 rounded shadow">
            <h2 className="text-center">Crea Domanda</h2>
            <form onSubmit={inviaDomanda}>
              <div className="form-group">
                <label
                  htmlFor="testoDomanda"
                  className="form-label mt-4 fw-semibold"
                >
                  Testo Domanda
                </label>
                <textarea
                  id="testoDomanda"
                  className="form-control"
                  value={testoDomanda}
                  placeholder="Inserisci il testo della domanda"
                  onChange={(e) => setTestoDomanda(e.target.value)}
                ></textarea>
              </div>

              <div className="form-group mt-4">
                <label className="form-label fw-semibold">Risposte</label>
                {risposte.map((risposta, index) => (
                  <div key={index} className="d-flex align-items-center mb-2">
                    <input
                      type="text"
                      className="form-control me-2"
                      value={risposta.testo || ""}
                      onChange={(e) => aggiornaRisposta(index, e.target.value)}
                      placeholder={`Risposta ${index + 1}`}
                    />
                    <input
                      type="radio"
                      className="form-check-input me-2"
                      checked={risposta.corretta}
                      onChange={() => selezionaCorretta(index)}
                      name="corretta"
                    />
                    <button
                      type="button"
                      className="btn btn-danger mt-2 d-flex align-items-center mb-1"
                      onClick={() => rimuoviRisposta(index)}
                    >
                      <DashCircle />
                    </button>
                  </div>
                ))}
                <button
                  type="button"
                  className="btn btn-outline-primary mt-2 me-2"
                  onClick={aggiungiRisposta}
                >
                  <PlusCircle /> Aggiungi Risposta
                </button>
              </div>

              <div className="d-flex justify-content-center">
                <button type="submit" className="btn btn-primary mt-3">
                  Crea
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
