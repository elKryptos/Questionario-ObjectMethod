import { useEffect, useState, useRef } from "react";
import { DomandaProps } from "../models/domanda";
import { useAuth } from "../context/AuthProvider";
import { useLocation, useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import Domanda from "./Domanda";
import Countdown from "./Countdown";

// Nel componente Questionario
const Questionario = () => {
  const [isFetching, setIsFetching] = useState<boolean>(false);
  const [domande, setDomande] = useState<DomandaProps["domanda"][]>([]);
  const [risposte, setRisposte] = useState<Record<number, string>>({});
  const risposteRef = useRef(risposte); // Memorizza le risposte più recenti
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const { utenteTestId } = location.state || {};

  // Sincronizza il riferimento con lo stato
  useEffect(() => {
    risposteRef.current = risposte;
  }, [risposte]);

  const handleInputChange = (domandaId: number, rispostaId: string) => {
    setRisposte((prevRisposte) => ({
      ...prevRisposte,
      [domandaId]: rispostaId,
    }));
  };

  const submitAnswers = async () => {
    if (!utenteTestId) {
      toast.error("Errore: ID utente non fornito!");
      return;
    }

    const risposteCorrenti = risposteRef.current; // Usa il riferimento

    if (Object.keys(risposteCorrenti).length === 0) {
      toast.error("Seleziona almeno una risposta!");
      return;
    }

    const payload = Object.entries(risposteCorrenti).map(
      ([domandaId, rispostaId]) => ({
        domandaId: Number(domandaId),
        rispostaId: Number(rispostaId),
        utenteTestId: utenteTestId,
      })
    );

    try {
      // Aggiorna l'utenteTest
      const response = await fetch(
        `http://localhost:8080/api/utenteTest/${utenteTestId}/concludi`,
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
        const punteggioOttenuto = await response.json();
        console.log(punteggioOttenuto);
        toast.success("Test completato con successo!");
        navigate("/questionario/risultato", { state: { punteggioOttenuto } });
      } else {
        toast.error("Errore durante l'invio delle risposte.");
      }
    } catch (error) {
      console.error("Errore:", error);
      toast.error("Errore durante l'invio delle risposte.");
    }
  };

  const handleTimerEnd = () => {
    toast.error("Tempo scaduto! Invio automatico delle risposte.");
    submitAnswers(); // Invio automatico delle risposte
  };

  // Carica le domande come prima
  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsFetching(true);

    fetch("http://localhost:8080/api/domande", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) throw new Error(response.statusText);
        return response.json();
      })
      .then((data) => {
        setDomande(data);
        setIsFetching(false);
      })
      .catch((error) => {
        console.error("Errore nel caricamento delle domande:", error);
        setIsFetching(false);
      });
  }, [isAuthenticated]);

  return (
    <div className="container py-5">
      <div className="row mb-4">
        <div className="col-12 d-flex justify-content-between align-items-center">
          <h2 className=" display-4 fw-bold mb-4 text-white text-center">
            Questionario
          </h2>
          <div className="">
            <Countdown onTimerEnd={handleTimerEnd} />
          </div>
        </div>
      </div>
      <form
        onSubmit={(e) => {
          e.preventDefault();
          submitAnswers();
        }}
      >
        <div className="mb-4">
          {isFetching ? (
            <div className="d-flex justify-content-center">
              <div className="spinner-border text-light" role="status">
                <span className="visually-hidden">Loading...</span>
              </div>
            </div>
          ) : (
            domande
              .slice(0, 10)
              .map((domanda) => (
                <Domanda
                  key={domanda.domandaId}
                  domanda={domanda}
                  onInputChange={handleInputChange}
                />
              ))
          )}
        </div>
        <div className="d-flex justify-content-center">
          <button type="submit" className="btn btn-light">
            Invia
          </button>
        </div>
      </form>
    </div>
  );
};

export default Questionario;
