package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.TypeReparationDTO;
import com.gestion.atelier.models.TypeReparation;

@Mapper
public interface TypeReparationMapper {
    TypeReparationMapper INSTANCE = Mappers.getMapper(TypeReparationMapper.class);

    TypeReparationDTO typeReparationToTypeReparationDTO(TypeReparation typeReparation);

    TypeReparation typeReparationDTOToTypeReparation(TypeReparationDTO typeReparationDTO); 
}
