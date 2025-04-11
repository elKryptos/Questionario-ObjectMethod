package it.objectmethod.questionario2024.mapper;

import it.objectmethod.questionario2024.dto.TestDomandaDto;
import it.objectmethod.questionario2024.entity.Risposta;
import it.objectmethod.questionario2024.entity.TestDomanda;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {DomandaMapper.class, RispostaMapper.class})
public interface TestDomandaMapper extends BaseMappingMethods<TestDomandaDto, TestDomanda> {

    @Mapping(target = "domandaId", source = "testDomanda.domanda.domandaId")
    @Mapping(target = "nomeTest", source = "testDomanda.test.nomeTest")
    @Mapping(target = "durataMinuti", source = "testDomanda.test.durataMinuti")
    @Mapping(target = "testoDomanda", source = "testDomanda.domanda.testoDomanda")
    @Mapping(target = "testoRisposta", source = "risposta.testoRisposta")
    @Mapping(target = "corretta", source = "risposta.corretta")
    TestDomandaDto toDto(TestDomanda testDomanda, Risposta risposta);

    TestDomanda toEntity(TestDomandaDto testDomandaDto);

    List<TestDomandaDto> toDtoList(List<TestDomanda> testDomandaList);

    List<TestDomanda> toEntityList(List<TestDomandaDto> testDomandaDtoList);
}
