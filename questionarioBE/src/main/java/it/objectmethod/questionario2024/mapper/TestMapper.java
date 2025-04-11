package it.objectmethod.questionario2024.mapper;

import it.objectmethod.questionario2024.dto.TestDto;
import it.objectmethod.questionario2024.entity.Test;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TestMapper extends BaseMappingMethods<TestDto, Test> {
    TestDto toDto(Test test);

    Test toEntity(TestDto testDto);

    List<TestDto> toDtoList(List<Test> testList);

    List<Test> toEntityList(List<TestDto> testDtoList);
}
