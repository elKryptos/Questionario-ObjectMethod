import { useEffect, useState } from "react";
import { Table, Container, Row, Col, Spinner } from "react-bootstrap";
import { Utente } from "../models/utente";

const Utenti = () => {
  const [utenti, setUtenti] = useState<Utente[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchUtenti = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/utente", {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
        if (!response.ok) {
          throw new Error("Errore nel recupero degli utenti");
        }
        const data: Utente[] = await response.json();
        setUtenti(data);
      } catch (err) {
        setError(err instanceof Error ? err.message : "Errore sconosciuto");
      } finally {
        setIsLoading(false);
      }
    };

    fetchUtenti();
  }, []);

  if (isLoading) {
    return (
      <Container className="d-flex justify-content-center align-items-center">
        <Spinner animation="border" variant="light" role="status">
          <span className="visually-hidden">Caricamento...</span>
        </Spinner>
      </Container>
    );
  }

  return (
    <Container className="mt-5">
      <Row className="justify-content-center">
        <Col>
          <h2 className="text-white text-center mb-4">
            Elenco Utenti Registrati
          </h2>
          {error ? (
            <p className="text-danger text-center">{error}</p>
          ) : (
            <Table striped bordered hover responsive variant="dark">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Nome</th>
                  <th>Email</th>
                  <th>Data di Nascita</th>
                  <th>Indirizzo</th>
                  <th>Admin</th>
                </tr>
              </thead>
              <tbody>
                {utenti.map((utente) => (
                  <tr key={utente.utenteId}>
                    <td>{utente.utenteId}</td>
                    <td>{utente.nomeUtente}</td>
                    <td>{utente.email}</td>
                    <td>{new Date(utente.compleanno).toLocaleDateString()}</td>
                    <td>{utente.indirizzo}</td>
                    <td>{utente.isAdmin ? "Sì" : "No"}</td>
                  </tr>
                ))}
              </tbody>
            </Table>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default Utenti;
