import { Col, Container, Row } from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";
import { useSpring, animated } from "@react-spring/web";

export default function Risultato() {
  const location = useLocation();
  const navigate = useNavigate();
  const { punteggioOttenuto = 0 } = location.state || {};

  const getRisultato = (punteggio: number) => {
    if (punteggio < 6) {
      return {
        title: "Insufficiente",
        text: "Hai ottenuto un punteggio inferiore al 50% delle persone che hanno svolto questo test.",
        textColor: "text-danger",
      };
    } else if (punteggio >= 6 && punteggio <= 8) {
      return {
        title: "Buono",
        text: "Hai ottenuto un punteggio superiore al 50% delle persone che hanno svolto questo test.",
        textColor: "text-success",
      };
    } else {
      return {
        title: "Ottimo",
        text: "Hai ottenuto un punteggio superiore al 65% delle persone che hanno svolto questo test.",
        textColor: "text-primary",
      };
    }
  };

  const risultato = getRisultato(punteggioOttenuto);

  // Animazioni
  const scoreAnimation = useSpring({
    from: { opacity: 0, scale: 0 },
    to: { opacity: 1, scale: 1 },
    config: { tension: 200, friction: 10 },
  });

  const textAnimation = useSpring({
    from: { opacity: 0, transform: "translateY(20px)" },
    to: { opacity: 1, transform: "translateY(0)" },
    delay: 400,
  });

  return (
    <Container>
      <Row className="justify-content-center">
        <Col className="text-center col-12 col-sm-8 col-md-6 col-lg-3 p-3">
          <div className="p-5 bg-white rounded shadow mt-5 justify-content-center">
            <p className="text-uppercase text-secondary">Il tuo risultato</p>
            <animated.div
              className="d-flex align-items-center justify-content-center text-white mx-auto my-3 circle"
              style={scoreAnimation}
            >
              <div className="d-flex flex-column align-items-center justify-content-center mt-3">
                <h3 className="mb-0 display-4 fw-bold">{punteggioOttenuto}</h3>
                <p className="fst-italic mb-2">su 10</p>
              </div>
            </animated.div>
            <animated.div style={textAnimation}>
              <h3
                className={`mt-3 text-primary fw-bold ${risultato.textColor}`}
              >
                {risultato.title}
              </h3>
              <p className="text-muted">{risultato.text}</p>
            </animated.div>
            <button
              className="btn btn-primary mt-3"
              onClick={() => navigate("/")}
            >
              Ripeti Test
            </button>
          </div>
        </Col>
      </Row>
    </Container>
  );
}
