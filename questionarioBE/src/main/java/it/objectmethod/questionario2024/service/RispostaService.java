package it.objectmethod.questionario2024.service;

import it.objectmethod.questionario2024.dto.RispostaDto;
import it.objectmethod.questionario2024.entity.Risposta;
import it.objectmethod.questionario2024.mapper.RispostaMapper;
import it.objectmethod.questionario2024.repository.RispostaRepository;
import it.objectmethod.questionario2024.specification.RispostaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RispostaService {

    private final RispostaRepository rispostaRepository;
    private final RispostaMapper rispostaMapper;

    public List<RispostaDto> findWithSpecification(final RispostaSpecification rispostaSpecification) {
        List<Risposta> risposteList = rispostaRepository.findAll(rispostaSpecification.toSpecification());
        return rispostaMapper.toDtoList(risposteList);
    }

}
