package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.Constants;
import it.objectmethod.questionario2024.dto.RispostaUtenteDto;
import it.objectmethod.questionario2024.dto.UtenteTestDto;
import it.objectmethod.questionario2024.entity.UtenteTest;
import it.objectmethod.questionario2024.mapper.UtenteTestMapper;
import it.objectmethod.questionario2024.repository.UtenteTestRepository;
import it.objectmethod.questionario2024.utils.BearerTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteTestService {
    private final UtenteTestMapper utenteTestMapper;
    private final UtenteTestRepository utenteTestRepository;
    private final RispostaUtenteService rispostaUtenteService;

    @Autowired
    BearerTokenUtil bearerTokenUtil;

    public UtenteTestDto createUtenteTest(final UtenteTestDto utenteTestDto, HttpServletRequest request) {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        } else {
            throw new IllegalArgumentException(Constants.USER_NOT_FOUND.getMessage());
        }
        Long utenteId = bearerTokenUtil.getUtenteId(token);

        Date dataInizio = new Date();
        utenteTestDto.setTestId(2L);
        utenteTestDto.setDataInizio(dataInizio);
        utenteTestDto.setDataFine(null);
        utenteTestDto.setPunteggioTotale(0);
        utenteTestDto.setUtenteId(utenteId);

        return utenteTestMapper.toDto(utenteTestRepository.save(utenteTestMapper.toEntity(utenteTestDto)));
    }

    public Integer updateUtenteTest(Long utenteTestId, List<RispostaUtenteDto> risposteUtenteDto) {
        // Recupero l'entità UtenteTest esistente
        UtenteTest utenteTest = utenteTestRepository.findById(utenteTestId)
                .orElseThrow(() -> new IllegalArgumentException(Constants.TEST_NOT_FOUND.getMessage()));

        // Salva le risposte dell'utente e calcola il punteggio totale
        int punteggioTotale = rispostaUtenteService.salvaRisposteECalcolaPunteggio(utenteTestId,risposteUtenteDto);

        // Aggiorna i campi di UtenteTest
        utenteTest.setDataFine(new Date());
        utenteTest.setPunteggioTotale(punteggioTotale);
        // Salva l'entità aggiornata
        utenteTest = utenteTestRepository.save(utenteTest);

        return punteggioTotale;
    }
}
