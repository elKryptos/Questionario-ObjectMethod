import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./components/Login";
import Signup from "./components/Signup";
import MyNavbar from "./components/MyNavbar";
import Home from "./components/Home";
import Questionario from "./components/Questionario";
import CreaDomanda from "./components/CreaDomanda";
import PrivateRoute from "./routes/PrivateRoute";
import { ToastProvider } from "./context/ToastProvider";
import Risultato from "./components/Risultato";
import Utenti from "./components/Utenti";
import AdminRoute from "./routes/AdminRoute";
import GestioneDomanda from "./components/GestiondeDomanda";
import ModificaDomanda from "./components/ModificaDomanda";
function App() {
  return (
    <ToastProvider>
      <MyNavbar />

      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />

          {/* Rotte protette */}
          <Route
            path="/domanda/create"
            element={
              <AdminRoute>
                <CreaDomanda />
              </AdminRoute>
            }
          />
          <Route
            path="/domanda/edit"
            element={
              <AdminRoute>
                <GestioneDomanda />
              </AdminRoute>
            }
          />
          <Route
            path="/domanda/update"
            element={
              <AdminRoute>
                <ModificaDomanda />
              </AdminRoute>
            }
          />
          <Route
            path="/utenti"
            element={
              <PrivateRoute>
                <Utenti />
              </PrivateRoute>
            }
          />
          <Route
            path="/questionario"
            element={
              <PrivateRoute>
                <Questionario />
              </PrivateRoute>
            }
          />
          <Route
            path="/questionario/risultato"
            element={
              <PrivateRoute>
                <Risultato />
              </PrivateRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </ToastProvider>
  );
}

export default App;
