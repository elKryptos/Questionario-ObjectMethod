package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.Constants;
import it.objectmethod.questionario2024.dto.RispostaUtenteDto;
import it.objectmethod.questionario2024.entity.RispostaUtente;
import it.objectmethod.questionario2024.entity.Utente;
import it.objectmethod.questionario2024.entity.UtenteTest;
import it.objectmethod.questionario2024.mapper.RispostaUtenteMapper;
import it.objectmethod.questionario2024.repository.RispostaRepository;
import it.objectmethod.questionario2024.repository.RispostaUtenteRepository;
import it.objectmethod.questionario2024.repository.UtenteTestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RispostaUtenteService {

    private final RispostaUtenteMapper rispostaUtenteMapper;
    private final RispostaUtenteRepository rispostaUtenteRepository;
    private final UtenteTestRepository utenteTestRepository;
    private final RispostaRepository rispostaRepository;

    @Transactional
    public int salvaRisposteECalcolaPunteggio(Long utenteTestId, final List<RispostaUtenteDto> risposteUtenteDto) {
        int punteggioTotale = 0;

        for (RispostaUtenteDto dto : risposteUtenteDto) {
            RispostaUtente rispostaUtente = rispostaUtenteMapper.toEntity(dto);
            rispostaUtente.setUtenteTest(utenteTestRepository.findById(utenteTestId)
                    .orElseThrow(() -> new RuntimeException("RispostaUtente non trovato")));
            boolean isCorretta = rispostaRepository.findByRispostaIdAndCorrettaIsTrue(dto.getRispostaId()) != null;
            rispostaUtente.setPunteggioOttenuto(isCorretta ? 1 : 0);
            if (isCorretta) {
                punteggioTotale++;
            }
            rispostaUtenteRepository.save(rispostaUtente);
        }
        return punteggioTotale;
    }


//    public Integer salvaRisposte(final List<RispostaUtenteDto> risposteUtenteDto) {
//        int punteggioTotale = 0;
//        for (RispostaUtenteDto dto : risposteUtenteDto) {
//            RispostaUtente rispostaUtente = rispostaUtenteMapper.toEntity(dto);
//            boolean isCorretta = rispostaRepository.findByRispostaIdAndCorrettaIsTrue(dto.getRispostaId()) != null;
//            rispostaUtente.setPunteggioOttenuto(isCorretta ? 1 : 0);
//            if (isCorretta) {
//                punteggioTotale++;
//            }
//            rispostaUtenteRepository.save(rispostaUtente);
//        }
//        return punteggioTotale;
//
//    }
//
//    public int calcolaPunteggio(final List<RispostaUtenteDto> risposteUtenteDto) {
//        int punteggioTotale = 0;
//
//        for (RispostaUtenteDto dto : risposteUtenteDto) {
//            boolean isCorretta = rispostaRepository.findByRispostaIdAndCorrettaIsTrue(dto.getRispostaId()) != null;
//            if (isCorretta) {
//                punteggioTotale++;
//            }
//        }
//
//        return punteggioTotale;
//    }

}
