package it.objectmethod.questionario2024.mapper;

import it.objectmethod.questionario2024.dto.UtenteTestDto;
import it.objectmethod.questionario2024.entity.UtenteTest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UtenteTestMapper extends BaseMappingMethods<UtenteTestDto, UtenteTest> {
    @Mapping(target = "utenteId", source = "utente.utenteId")
    @Mapping(target = "testId", source = "test.testId")
    UtenteTestDto toDto(UtenteTest utenteTest);

    @Mapping(target = "utente.utenteId", source = "utenteId")
    @Mapping(target = "test.testId", source = "testId")
    UtenteTest toEntity(UtenteTestDto utenteTestDto);

    List<UtenteTestDto> toDtoList(List<UtenteTest> utenteTestList);

    List<UtenteTest> toEntityList(List<UtenteTestDto> utenteTestDtoList);
}
