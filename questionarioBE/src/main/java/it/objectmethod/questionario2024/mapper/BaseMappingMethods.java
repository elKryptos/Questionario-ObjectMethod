package it.objectmethod.questionario2024.mapper;


import java.util.List;

public interface BaseMappingMethods<DTO, ENTITY> {
    ENTITY toEntity(DTO dto);

    DTO toDto(ENTITY entity);

    List<ENTITY> toEntityList(List<DTO> dtoList);

    List<DTO> toDtoList(List<ENTITY> entityList);
}
