import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { DomandaProps } from "../models/domanda";
import toast from "react-hot-toast";

const ModificaDomanda = () => {
  const location = useLocation();
  const { domandaId } = location.state || {};
  const [domanda, setDomanda] = useState<DomandaProps["domanda"] | null>(null);
  const [risposte, setRisposte] = useState<DomandaProps["domanda"]["risposte"]>(
    []
  );
  const [isFetching, setIsFetching] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (domandaId) {
      findDomanda();
    }
  }, [domandaId]);

  const findDomanda = async () => {
    setIsFetching(true);
    try {
      const response = await fetch(
        `http://localhost:8080/api/domande/${domandaId}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      if (response.ok) {
        const data = await response.json();
        setDomanda(data);
        setRisposte(data.risposte);
      }
    } catch (error) {
      console.error(error);
    } finally {
      setIsFetching(false);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (domanda) {
      const payload = { ...domanda, risposte }; // Unisce le risposte modificate
      console.log("Payload inviato:", JSON.stringify(payload));

      try {
        const response = await fetch(
          `http://localhost:8080/api/domande/${domandaId}`,
          {
            method: "PUT",
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
          }
        );
        if (response.ok) {
          toast.success("Domanda modificata con successo!");
          navigate("/domanda/edit");
        } else {
          toast.error("Errore durante la modifica della domanda!");
        }
      } catch (error) {
        console.error("Errore durante la modifica della domanda:", error);
        toast.error("Si è verificato un errore durante la modifica.");
      }
    }
  };

  const aggiungiRisposta = () => {
    setRisposte([
      ...risposte,
      {
        domandaId: domandaId,
        testoRisposta: "",
        corretta: false,
        rispostaId: 0,
      },
    ]);
  };

  return (
    <div className="container py-5">
      <div className="row d-flex justify-content-center">
        <div className="col-8">
          <h2 className="mb-3 text-center text-white">Modifica Domanda</h2>
          <div className="card shadow-lg bg-dark text-white">
            <div className="card-body">
              {isFetching ? (
                <div className="d-flex justify-content-center">
                  <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Caricamento...</span>
                  </div>
                </div>
              ) : domanda ? (
                <form onSubmit={handleSubmit}>
                  <div className="mb-4">
                    <label className="form-label fw-bold">Testo Domanda</label>
                    <input
                      type="text"
                      className="form-control"
                      value={domanda.testoDomanda}
                      onChange={(e) =>
                        setDomanda({ ...domanda, testoDomanda: e.target.value })
                      }
                      placeholder="Inserisci il testo della domanda"
                    />
                  </div>
                  <div>
                    {risposte.map((risposta, index) => (
                      <div key={index} className="mb-4">
                        <label className="form-label fw-bold">
                          Risposta {index + 1}
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          value={risposta.testoRisposta}
                          onChange={(e) => {
                            const nuoveRisposte = [...risposte];
                            nuoveRisposte[index] = {
                              ...nuoveRisposte[index],
                              testoRisposta: e.target.value,
                            };
                            setRisposte(nuoveRisposte);
                          }}
                          placeholder={`Inserisci la risposta ${index + 1}`}
                        />
                        <div className="form-check mt-2">
                          <input
                            className="form-check-input"
                            type="checkbox"
                            checked={risposta.corretta}
                            onChange={(e) => {
                              const nuoveRisposte = [...risposte];
                              nuoveRisposte[index] = {
                                ...nuoveRisposte[index],
                                corretta: e.target.checked,
                              };
                              setRisposte(nuoveRisposte);
                            }}
                          />
                          <label className="form-check-label">
                            {risposta.corretta
                              ? "Risposta corretta"
                              : "Segna come corretta"}
                          </label>
                        </div>
                      </div>
                    ))}
                  </div>
                  <div className="d-flex justify-content-around">
                    <button
                      type="button"
                      onClick={aggiungiRisposta}
                      className="btn btn-success"
                    >
                      Aggiungi Risposta
                    </button>
                    <button
                      type="button"
                      className="btn btn-secondary"
                      onClick={() => navigate(-1)}
                    >
                      Annulla
                    </button>
                    <button type="submit" className="btn btn-primary">
                      Salva Modifiche
                    </button>
                  </div>
                </form>
              ) : (
                <p className="text-center text-danger">
                  Nessuna domanda trovata.
                </p>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ModificaDomanda;
