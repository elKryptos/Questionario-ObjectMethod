package it.objectmethod.questionario2024.mapper;

import it.objectmethod.questionario2024.dto.DomandaDto;
import it.objectmethod.questionario2024.dto.RispostaDto;
import it.objectmethod.questionario2024.entity.Domanda;
import it.objectmethod.questionario2024.entity.Risposta;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {RispostaMapper.class})
public interface DomandaMapper extends BaseMappingMethods<DomandaDto, Domanda> {

    DomandaDto toDto(final Domanda domanda);

    Domanda toEntity(final DomandaDto domandaDto);

    List<DomandaDto> toDtoList(final List<Domanda> domandeList);

    List<Domanda> toEntityList(final List<DomandaDto> domandeDtoList);

    @AfterMapping
    default void mappingRisposte(@MappingTarget Domanda domanda) {
        for (Risposta risposta : domanda.getRisposte()) {
            risposta.setDomanda(domanda);
        }
    }

//    @Mapping(target = "domandaId", ignore = true)
//    @Mapping(target = "risposte", ignore = true)
//        // Gestite manualmente
//    void updateDomanda(@MappingTarget Domanda domanda, DomandaDto domandaDto);
//
//    @AfterMapping
//    default void handleRisposte(@MappingTarget Domanda domanda, DomandaDto domandaDto) {
//        // Crea una mappa delle risposte esistenti
//        Map<Long, Risposta> existingRisposte = domanda.getRisposte().stream()
//                .collect(Collectors.toMap(Risposta::getRispostaId, r -> r));
//
//        // Aggiorna o ignora le risposte
//        domandaDto.getRisposte().forEach(rispostaDto -> {
//            if (rispostaDto.getRispostaId() != null) {
//                Risposta risposta = existingRisposte.get(rispostaDto.getRispostaId());
//                risposta.setTestoRisposta(rispostaDto.getTestoRisposta());
//                risposta.setCorretta(rispostaDto.getCorretta());
//            }
//        });
//
//        // Aggiorna la relazione domanda -> risposta
//        domanda.getRisposte().forEach(risposta -> risposta.setDomanda(domanda));
//    }
}
