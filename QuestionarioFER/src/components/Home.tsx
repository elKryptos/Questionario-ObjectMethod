import { Col, Container, Row } from "react-bootstrap";
import { useAuth } from "../context/AuthProvider";
import { useNavigate } from "react-router-dom";
import { Toaster, toast } from "react-hot-toast";
import { useState } from "react";

export default function Home() {
  const { isAuthenticated, utenteId } = useAuth();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false); // Stato per gestire il pulsante

  const handleStartQuestionnaire = async (e: React.FormEvent) => {
    e.preventDefault();
    if (isAuthenticated) {
      const payload = {
        testId: 1,
        utenteId: utenteId,
        dataInizio: new Date().toISOString(),
      };
      try {
        setLoading(true); // Disabilita il pulsante
        const response = await fetch("http://localhost:8080/api/utenteTest", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
          body: JSON.stringify(payload),
        });

        if (response.ok) {
          const data = await response.json();
          const utenteTestId = data.utenteTestId;
          console.log(utenteTestId);
          toast.success("Questionario avviato con successo!");
          navigate("/questionario", { state: { utenteTestId: utenteTestId } }); // Reindirizza al questionario
        } else {
          const errorData = await response.json();
          toast.error(
            errorData.message || "Errore durante l'avvio del questionario."
          );
        }
      } catch (error) {
        console.error("Errore:", error);
        toast.error("Si è verificato un errore. Riprova più tardi.");
      } finally {
        setLoading(false); // Riabilita il pulsante
      }
    } else {
      toast.error("Devi effettuare il login per accedere al questionario!");
      navigate("/login"); // Reindirizza alla pagina di login
    }
  };

  return (
    <Container className="mt-5 text-white">
      <Row>
        <Col className="text-center">
          <h1 className="display-4 fw-bold mb-4">Benvenuto al Questionario</h1>
          <p className="fs-4 mb-4">
            Questo è un semplice questionario per testare le tue conoscenze
            generali. Ti aiuterà a valutare le tue competenze e a fornirti un
            feedback personalizzato. Impiegherai solo pochi minuti!
          </p>
          <div className="d-flex justify-content-center">
            <button
              onClick={handleStartQuestionnaire}
              className="btn btn-light px-4 py-2 rounded-pill fw-semibold lg"
              disabled={loading} // Disabilita il pulsante durante il caricamento
            >
              {loading ? "Caricamento..." : "Inizia il questionario"}
            </button>
          </div>
        </Col>
      </Row>
      <Toaster position="top-center" reverseOrder={false} />
    </Container>
  );
}
