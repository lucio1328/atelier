package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.models.Reparations;

@Mapper
public interface ReparationsMapper {
    ReparationsMapper INSTANCE = Mappers.getMapper(ReparationsMapper.class);

    ReparationsDTO reparationsToReparationsDTO(Reparations reparations);

    Reparations reparationsDTOToReparations(ReparationsDTO reparationsDTO);
}
