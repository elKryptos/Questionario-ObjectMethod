import { createRoot } from "react-dom/client";
import "bootstrap/dist/css/bootstrap.min.css";
import "./index.css";
import App from "./App.tsx";
import { AuthProvider } from "./context/AuthProvider.tsx";

createRoot(document.getElementById("root")!).render(
  <AuthProvider>
    <App />
  </AuthProvider>
);
