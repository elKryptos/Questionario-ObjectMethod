import React, { useEffect, useState } from "react";
import { DomandaProps } from "../models/domanda";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { Modal, Button } from "react-bootstrap";

const GestioneDomanda: React.FC = () => {
  const [domande, setDomande] = useState<DomandaProps["domanda"][]>([]);
  const [isFetching, setIsFetching] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [domandaToDelete, setDomandaToDelete] = useState<number | null>(null); // Salva l'id della domanda da eliminare
  const navigate = useNavigate();

  useEffect(() => {
    fetchDomande();
  }, []);

  const fetchDomande = async () => {
    setIsFetching(true);
    try {
      const response = await fetch("http://localhost:8080/api/domande", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
      if (response.ok) {
        const data: DomandaProps["domanda"][] = await response.json();
        setDomande(data);
      }
    } catch (error) {
      console.error("Errore nel caricamento delle domande:", error);
    } finally {
      setIsFetching(false);
    }
  };

  const eliminaDomanda = async (id: number) => {
    try {
      const response = await fetch(`http://localhost:8080/api/domande/${id}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
      if (response.ok) {
        setDomande((prev) => prev.filter((d) => d.domandaId !== id));
        toast.success("Domanda eliminata con successo!");
        setShowModal(false); // Chiude il modal dopo l'eliminazione
      }
    } catch (error) {
      console.error("Errore durante l'eliminazione della domanda:", error);
      setShowModal(false); // Chiude il modal in caso di errore
    }
  };

  const modificaDomanda = (id: number) => {
    navigate("/domanda/update", { state: { domandaId: id } });
  };

  return (
    <div className="container py-5">
      <h2 className="text-white text-center mb-4">Gestione Domande</h2>
      {isFetching ? (
        <div className="d-flex justify-content-center">
          <div className="spinner-border text-light" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      ) : (
        <table className="table table-bordered table-hover table-striped table-dark shadow">
          <thead>
            <tr>
              <th>ID</th>
              <th>Testo</th>
              <th>Risposte</th>
              <th>Azioni</th>
            </tr>
          </thead>
          <tbody>
            {domande.map((domanda: DomandaProps["domanda"]) => (
              <tr key={domanda.domandaId}>
                <td className="align-middle">{domanda.domandaId}</td>
                <td className="align-middle">{domanda.testoDomanda}</td>
                <td>
                  <ul>
                    {domanda.risposte.map((risposta) => (
                      <li key={risposta.rispostaId}>
                        {risposta.testoRisposta}
                      </li>
                    ))}
                  </ul>
                </td>
                <td className="align-middle">
                  <div className="d-flex justify-content-center align-items-center">
                    <button
                      className="btn btn-primary btn-sm me-2"
                      onClick={() => modificaDomanda(domanda.domandaId)}
                    >
                      Modifica
                    </button>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => {
                        setDomandaToDelete(domanda.domandaId); // Salva l'id della domanda da eliminare
                        setShowModal(true); // Mostra il modal di conferma
                      }}
                    >
                      Elimina
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {/* Modal di conferma eliminazione */}
      <Modal
        show={showModal}
        onHide={() => setShowModal(false)}
        animation={true}
      >
        <Modal.Header closeButton>
          <Modal.Title>Conferma Eliminazione</Modal.Title>
        </Modal.Header>
        <Modal.Body>Sei sicuro di voler eliminare questa domanda?</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Annulla
          </Button>
          <Button
            variant="danger"
            onClick={() => {
              if (domandaToDelete !== null) {
                eliminaDomanda(domandaToDelete);
              }
            }}
          >
            Elimina
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default GestioneDomanda;
