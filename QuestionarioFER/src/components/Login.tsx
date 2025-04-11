import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthProvider";
import { useToast } from "../context/ToastProvider";
import CardContainer from "./CardContainer";
import toast, { Toaster } from "react-hot-toast";

export default function Login() {
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [error, setError] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const { login } = useAuth();
  const { showToast, setPersistentToast } = useToast();
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError(null); // Resetta l'errore prima del nuovo tentativo
    setIsLoading(true);
    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
      });
      setIsLoading(false);

      if (response.status === 401) {
        toast.error("Credenziali non valide. Riprova.");
        return;
      }

      if (response.ok) {
        const data = await response.json();
        login(data.response, data.isAdmin, data.utenteId);
        setPersistentToast("success", "Login effettuato con successo!");
        navigate("/");
      } else {
        setError("Errore durante il login. Riprova più tardi.");
      }
    } catch (error) {
      setIsLoading(false);
      setError(
        "Si è verificato un errore imprevisto. Riprova più tardi." + error
      );
    }
  };

  return (
    <CardContainer title="Login">
      {error && <div className="alert alert-danger text-center">{error}</div>}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="email" className="form-label mt-3 fw-semibold">
            Email
          </label>
          <input
            type="text"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            className="form-control"
            placeholder="Inserisci la tua email"
            disabled={isLoading}
          />
        </div>
        <div className="form-group">
          <label htmlFor="password" className="form-label mt-3 fw-semibold">
            Password
          </label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            className="form-control"
            placeholder="Inserisci la tua password"
            disabled={isLoading}
          />
        </div>
        <div className="form-group form-check mt-3">
          <input
            type="checkbox"
            className="form-check-input"
            id="exampleCheck1"
            name="rememberMe"
            disabled={isLoading}
          />
          <label className="form-check-label" htmlFor="exampleCheck1">
            Ricordami
          </label>
        </div>
        <button
          disabled={isLoading}
          type="submit"
          className="btn btn-primary w-100 mt-3"
        >
          {isLoading && (
            <>
              <span
                className="spinner-border spinner-border-sm"
                aria-hidden="true"
              ></span>
              <span className="visually-hidden" role="status">
                Loading...
              </span>
            </>
          )}
          Accedi
        </button>
        <a
          href="/signup"
          className="d-block text-center mt-3 text-decoration-none"
        >
          Non hai un account? Registrati
        </a>
      </form>
      <Toaster position="top-center" reverseOrder={false} />
    </CardContainer>
  );
}
