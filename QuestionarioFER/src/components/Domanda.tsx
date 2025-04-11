import { FiHelpCircle } from "react-icons/fi";
import { DomandaProps } from "../models/domanda";

export default function Domanda({ domanda, onInputChange }: DomandaProps) {
  console.log(domanda);
  return (
    <div className="mb-4 p-4 bg-white rounded shadow">
      <div className="d-flex flex-column align-items-start mb-3">
        <h5 className="fw-bold text-dark">
          <FiHelpCircle className="me-2 mb-1" />
          {domanda.testoDomanda}
        </h5>
        <div className="ms-4">
          {domanda.risposte.map((opzione) => (
            <div key={opzione.rispostaId} className="form-check">
              <input
                className="form-check-input"
                type="radio"
                name={`risposta${domanda.domandaId}`}
                id="flexCheckDefault"
                value={opzione.rispostaId}
                onChange={(e) => {
                  console.log("rispostaId selezionata: ", e.target.value);
                  onInputChange(domanda.domandaId, e.target.value);
                }}
              />
              <label className="form-check-label" htmlFor="flexCheckDefault">
                {opzione.testoRisposta}
              </label>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
