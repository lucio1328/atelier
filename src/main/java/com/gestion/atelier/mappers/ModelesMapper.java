package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.ModelesDTO;
import com.gestion.atelier.models.Modeles;

@Mapper
public interface ModelesMapper {
    ModelesMapper INSTANCE = Mappers.getMapper(ModelesMapper.class);

    ModelesDTO modelesToModelesDTO(Modeles modeles);

    Modeles modelesDTOToModeles(ModelesDTO modelesDTO);
}
