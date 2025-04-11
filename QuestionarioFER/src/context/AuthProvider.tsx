import React, { createContext, useContext, useEffect, useState } from "react";

const AuthContext = createContext<{
  isAuthenticated: boolean;
  isAdmin: boolean;
  utenteId: number;
  login: (token: string, isAdmin: boolean, utenteId: number) => void;
  logout: () => void;
  loading: boolean;
}>({
  isAuthenticated: false,
  isAdmin: false,
  utenteId: 0,
  login: (token: string, isAdmin: boolean, utenteId: number) => {},
  logout: () => {},
  loading: true,
});

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [utenteId, setUtenteId] = useState(0);
  const [loading, setLoading] = useState(true);

  // Effettua un controllo sul token al caricamento della pagina
  useEffect(() => {
    const token = localStorage.getItem("token");
    const isAdminValue = localStorage.getItem("isAdmin") === "true";
    const utenteIdValue = localStorage.getItem("utenteId");
    setLoading(false);
    if (token) {
      setIsAuthenticated(true);
      setIsAdmin(isAdminValue);
      setUtenteId(Number(utenteIdValue));
    } else {
      setIsAuthenticated(false);
      setIsAdmin(false);
      setUtenteId(0);
    }
  }, []);

  const login = (token: string, isAdmin: boolean, utenteId: number) => {
    if (!token) {
      console.error("Token non valido.");
      return;
    }
    localStorage.setItem("token", token);
    localStorage.setItem("isAdmin", String(isAdmin));
    localStorage.setItem("utenteId", String(utenteId));
    setIsAuthenticated(true);
    setIsAdmin(isAdmin);
    setUtenteId(utenteId);
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("isAdmin");
    localStorage.removeItem("utenteId");
    setIsAuthenticated(false);
    setIsAdmin(false);
    setUtenteId(0);
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, isAdmin, utenteId, login, logout, loading }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
