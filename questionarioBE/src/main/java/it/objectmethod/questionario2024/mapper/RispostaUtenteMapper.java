package it.objectmethod.questionario2024.mapper;

import it.objectmethod.questionario2024.dto.RispostaUtenteDto;
import it.objectmethod.questionario2024.entity.RispostaUtente;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RispostaUtenteMapper extends BaseMappingMethods<RispostaUtenteDto, RispostaUtente> {
    RispostaUtenteDto toDto(final RispostaUtente rispostaUtente);

    @Mapping(target = "utenteTest.utenteTestId", source = "utenteTestId")
    @Mapping(target = "risposta.rispostaId", source = "rispostaId")
    @Mapping(target = "domanda.domandaId", source = "domandaId")
    RispostaUtente toEntity(final RispostaUtenteDto rispostaUtenteDto);

    List<RispostaUtenteDto> toDtoList(final List<RispostaUtente> risposteUtenteList);

    @Mapping(target = "utenteTest.utenteTestId", source = "utenteTestId")
    @Mapping(target = "risposta.rispostaId", source = "rispostaId")
    @Mapping(target = "domanda.domandaId", source = "domandaId")
    List<RispostaUtente> toEntityList(final List<RispostaUtenteDto> risposteUtenteDtoList);
}
