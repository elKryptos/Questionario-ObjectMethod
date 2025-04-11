package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.Constants;
import it.objectmethod.questionario2024.dto.DomandaDto;
import it.objectmethod.questionario2024.dto.RispostaDto;
import it.objectmethod.questionario2024.entity.Domanda;
import it.objectmethod.questionario2024.entity.Risposta;
import it.objectmethod.questionario2024.exception.NotFoundException;
import it.objectmethod.questionario2024.mapper.DomandaMapper;
import it.objectmethod.questionario2024.mapper.RispostaMapper;
import it.objectmethod.questionario2024.repository.DomandaRepository;
import it.objectmethod.questionario2024.repository.RispostaRepository;
import it.objectmethod.questionario2024.specification.DomandaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DomandaService {

    private final DomandaMapper domandaMapper;
    private final DomandaRepository domandaRepository;
    private final RispostaRepository rispostaRepository;
    private final RispostaMapper rispostaMapper;

    public List<DomandaDto> getAllDomande() {
        List<Domanda> domandeList = domandaRepository.findAll();
        return domandaMapper.toDtoList(domandeList);
    }

    public DomandaDto getDomandaById(final Long id) {
        Domanda domanda = domandaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.QUESTION_NOT_FOUND.getMessage()));
        return domandaMapper.toDto(domanda);
    }

    public DomandaDto createDomanda(final DomandaDto domandaDto) {
        Domanda domanda = domandaMapper.toEntity(domandaDto);
        Domanda savedDomanda = domandaRepository.save(domanda);
        return domandaMapper.toDto(savedDomanda);
    }

    public DomandaDto updateDomanda(final Long id, final DomandaDto domandaDto) {
        Domanda domanda = domandaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.QUESTION_NOT_FOUND.getMessage()));
        domanda.setTestoDomanda(domandaDto.getTestoDomanda());
        updateRisposte(domanda, domandaDto);
        Domanda savedDomanda = domandaRepository.save(domanda);
        return domandaMapper.toDto(savedDomanda);
    }

    public String deleteDomanda(final Long id) {
        Domanda domanda = domandaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.QUESTION_NOT_FOUND.getMessage()));
        domandaRepository.delete(domanda);
        return Constants.QUESTION_DELETED.getMessage();
    }

    public List<DomandaDto> getDomandeBySpecification(final DomandaSpecification domandaSpecification) {
        List<Domanda> domandaList = domandaRepository.findAll(domandaSpecification.toSpecification());
        return domandaMapper.toDtoList(domandaList);
    }

    private void updateRisposte(Domanda domanda, DomandaDto domandaDto) {
        Map<Long, Risposta> existingRisposte = domanda.getRisposte()
                .stream()
                .collect(Collectors.toMap(Risposta::getRispostaId, r -> r));

        List<Risposta> risposteToSave = new ArrayList<>();

        // Gestisci le risposte nel DTO
        domandaDto.getRisposte().forEach(rispostaDto -> {
            if (rispostaDto.getRispostaId() != null) {
                // Se la risposta esiste già, aggiorna
                Risposta risposta = existingRisposte.get(rispostaDto.getRispostaId());
                if (risposta != null) {
                    risposta.setTestoRisposta(rispostaDto.getTestoRisposta());
                    risposta.setCorretta(rispostaDto.getCorretta());
                }
            } else {
                // Se la risposta non ha ID, è una nuova risposta, quindi creala
                Risposta nuovaRisposta = rispostaMapper.toEntity(rispostaDto);
                nuovaRisposta.setDomanda(domanda);
                risposteToSave.add(nuovaRisposta);
            }
        });

        // Salva tutte le nuove risposte in un'unica operazione
        if (!risposteToSave.isEmpty()) {
            rispostaRepository.saveAll(risposteToSave);
        }

        // Rimuovi le risposte che non sono più presenti nel DTO
        domanda.getRisposte().removeIf(risposta ->
                domandaDto.getRisposte().stream()
                        .noneMatch(dto -> dto.getRispostaId() != null && dto.getRispostaId().equals(risposta.getRispostaId())));

        // Associa la domanda a tutte le risposte (utile se non già gestito dal mapper)
        domanda.getRisposte().forEach(risposta -> risposta.setDomanda(domanda));
    }
}
