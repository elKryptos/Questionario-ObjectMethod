import { Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthProvider";

export default function PrivateRoute({
  children,
}: {
  children: React.ReactNode;
}) {
  const { isAuthenticated, loading } = useAuth();

  if (loading) {
    // Mostra uno spinner o un caricamento mentre si verifica l'autenticazione
    return (
      <div className="d-flex justify-content-center">
        <div className="spinner-border text-light" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  if (!isAuthenticated) {
    // Reindirizza al login se non autenticato
    return <Navigate to="/login" />;
  }

  // Rende il contenuto protetto se autenticato
  return children;
}
