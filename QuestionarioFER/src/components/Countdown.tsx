import { CountdownCircleTimer } from "react-countdown-circle-timer";
import { CountdownProps } from "../models/countdown";

const Countdown: React.FC<CountdownProps> = ({ onTimerEnd }) => {
  // Funzione per rendere il tempo rimanente in formato MM:SS
  const renderTime = ({ remainingTime }: { remainingTime: number }) => {
    if (remainingTime === 0) {
      return <div className="text-white text-center">Tempo scaduto!</div>;
    }

    // Calcolo minuti e secondi
    const minutes = Math.floor(remainingTime / 60);
    const seconds = remainingTime % 60;

    return (
      <div className=" text-white text-center">
        <div className="fst-italic">
          {minutes}:{seconds < 10 ? `0${seconds}` : seconds}
        </div>
      </div>
    );
  };

  return (
    <div className="">
      <CountdownCircleTimer
        isPlaying
        duration={10 * 60} // Durata totale in secondi (10 minuti)
        colors={["#00BEAC", "#F7B801", "#A30000", "#A30000"]}
        colorsTime={[10 * 60, 5 * 60, 2 * 60, 0]} // Cambia colore in base al tempo rimanente
        size={100}
        onComplete={() => {
          onTimerEnd(); // Chiama la funzione quando il timer termina
          return { shouldRepeat: false }; // Impedisce la ripetizione automatica
        }}
      >
        {renderTime}
      </CountdownCircleTimer>
    </div>
  );
};

export default Countdown;
