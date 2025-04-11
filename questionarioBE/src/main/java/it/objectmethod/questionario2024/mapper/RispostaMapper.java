package it.objectmethod.questionario2024.mapper;

import it.objectmethod.questionario2024.dto.RispostaDto;
import it.objectmethod.questionario2024.entity.Risposta;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface RispostaMapper extends BaseMappingMethods<RispostaDto, Risposta> {
    @Mapping(target = "domandaId", source = "domanda.domandaId")
    RispostaDto toDto(final Risposta risposta);

    @Mapping(target = "domanda.domandaId", source = "domandaId")
    Risposta toEntity(final RispostaDto rispostaDto);


    List<RispostaDto> toDtoList(final List<Risposta> risposteList);

    List<Risposta> toEntityList(final List<RispostaDto> risposteDtoList);
}
