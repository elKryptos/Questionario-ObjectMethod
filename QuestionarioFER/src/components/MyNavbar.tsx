import { Container, Navbar, NavDropdown } from "react-bootstrap";
import Nav from "react-bootstrap/Nav";
import logo from "../assets/object_method_srl_logo.jpg";
import { useAuth } from "../context/AuthProvider";
import { BsFillPersonFill } from "react-icons/bs";

export default function MyNavbar() {
  const { isAuthenticated, isAdmin, logout } = useAuth();
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="/">
          <img
            alt=""
            src={logo}
            width="40"
            height="30"
            className="d-inline-block align-top me-1"
          />
          Questionario
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
          <Nav>
            {isAdmin && (
              <NavDropdown title="Admin" id="basic-nav-dropdown">
                <NavDropdown.Item href="/utenti">
                  Utenti Registrati
                </NavDropdown.Item>
                <NavDropdown.Item href="/domanda/create">
                  Crea Domanda
                </NavDropdown.Item>
                <NavDropdown.Item href="/domanda/edit">
                  Gestione Domande
                </NavDropdown.Item>
              </NavDropdown>
            )}
            {isAuthenticated ? (
              <Nav.Link href="/" onClick={logout}>
                <BsFillPersonFill /> Logout
              </Nav.Link>
            ) : (
              <Nav.Link href="/login">Accedi</Nav.Link>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
