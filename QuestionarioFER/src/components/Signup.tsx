import { useState } from "react";
import toast, { Toaster } from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { useToast } from "../context/ToastProvider";
import CardContainer from "./CardContainer";

export default function Signup() {
  const [userData, setUserData] = useState({
    nomeUtente: "",
    compleanno: "",
    indirizzo: "",
    email: "",
    password: "",
    isAdmin: 0,
  });
  const [error, setError] = useState<string | null>(null);
  const { showToast, setPersistentToast } = useToast();
  const [touched, setTouched] = useState({
    nomeUtente: false,
    email: false,
    password: false,
    indirizzo: false,
  }); // Stato per tracciare se l'utente ha toccato un campo
  const navigate = useNavigate();

  const validateNomeUtente = (nomeUtente: string) => {
    const regex = /^[a-zA-Z\s]{3,}$/; // Almeno 3 caratteri alfabetici
    return regex.test(nomeUtente);
  };

  const validateEmail = (email: string) => {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Formato email valido
    return regex.test(email);
  };

  const validatePassword = (password: string) => {
    const regex =
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*(),.?":{}|<>-]{8,}$/;
    // Almeno 8 caratteri, almeno una lettera (maiuscola e minuscola), almeno un numero, caratteri speciali opzionali
    return regex.test(password);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setUserData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleBlur = (e: React.FocusEvent<HTMLInputElement>) => {
    const { name } = e.target;
    setTouched((prevTouched) => ({
      ...prevTouched,
      [name]: true,
    }));
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(userData);
    if (!validateNomeUtente(userData.nomeUtente)) {
      setError("Il nome utente deve contenere almeno 3 caratteri alfabetici.");
      return;
    }
    if (!validateEmail(userData.email)) {
      setError("L'email deve essere valida.");
      return;
    }
    if (!validatePassword(userData.password)) {
      setError(
        "La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola, almeno una lettera minuscola e almeno un numero."
      );
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/auth/signin", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });
      console.log(response);
      if (response.ok) {
        const data = await response.json();
        console.log(data);
        setPersistentToast("success", "Registrazione effettuata con successo!");
        navigate("/login");
      } else {
        toast.error("Errore durante la registrazione. Riprova.");
      }
    } catch (error) {
      setError("Si è verificato un errore. Riprova più tardi." + error);
    }
  };

  return (
    <>
      <CardContainer title="Registrazione">
        {error && <p className="text-danger text-center">{error}</p>}
        <form onSubmit={handleSubmit} className="form-group">
          <div className="form-group">
            <label
              htmlFor="exampleInputName"
              className="form-label mt-4 fw-semibold"
            >
              Nome
            </label>
            <input
              type="text"
              name="nomeUtente"
              className="form-control"
              id="exampleInputName"
              required
              value={userData.nomeUtente}
              onChange={handleChange}
              onBlur={handleBlur}
            />
          </div>
          {touched.nomeUtente && !validateNomeUtente(userData.nomeUtente) && (
            <small className="text-danger">
              Il nome utente deve contenere almeno 3 caratteri alfabetici.
            </small>
          )}
          <div className="form-group">
            <label
              htmlFor="exampleInputDate"
              className="form-label mt-4 fw-semibold"
            >
              Data di nascita
            </label>
            <input
              type="date"
              name="compleanno"
              className="form-control"
              id="exampleInputDate"
              required
              value={userData.compleanno}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label
              htmlFor="exampleInputIndirizzo"
              className="form-label mt-4 fw-semibold"
            >
              Indirizzo
            </label>
            <input
              type="text"
              name="indirizzo"
              className="form-control"
              id="exampleInputIndirizzo"
              required
              value={userData.indirizzo}
              onChange={handleChange}
            />
          </div>
          <div className="form-group">
            <label
              htmlFor="exampleInputEmail"
              className="form-label mt-3 fw-semibold"
            >
              Email
            </label>
            <input
              type="email"
              name="email"
              className="form-control"
              id="exampleInputEmail"
              required
              value={userData.email}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            {touched.email && !validateEmail(userData.email) && (
              <small className="text-danger">L'email deve essere valida.</small>
            )}
          </div>
          <div className="form-group">
            <label
              htmlFor="exampleInputPassword"
              className="form-label mt-3 fw-semibold"
            >
              Password
            </label>
            <input
              type="password"
              name="password"
              className="form-control"
              id="exampleInputPassword"
              required
              value={userData.password}
              onChange={handleChange}
              onBlur={handleBlur}
            />
            {touched.password && !validatePassword(userData.password) && (
              <small className="text-danger">
                La password deve contenere almeno 8 caratteri, una lettera
                maiuscola, una lettera minuscola e almeno un numero.
              </small>
            )}
          </div>
          <div className="d-flex justify-content-center mt-3">
            <button type="submit" className="btn btn-primary btn-block mt-3">
              Crea l'account
            </button>
          </div>
        </form>
        <div className="text-center mt-3">
          <a href="/login" className="text-decoration-none">
            Possiedi già un account? Fai il login da qui.
          </a>
        </div>
      </CardContainer>
      <Toaster position="top-center" reverseOrder={false} />
    </>
  );
}
