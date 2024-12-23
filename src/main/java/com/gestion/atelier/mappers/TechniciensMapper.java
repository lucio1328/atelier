package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.TechniciensDTO;
import com.gestion.atelier.models.Techniciens;

@Mapper
public interface TechniciensMapper {
    TechniciensMapper INSTANCE = Mappers.getMapper(TechniciensMapper.class);

    TechniciensDTO techniciensToTechniciensDTO(Techniciens techniciens);

    Techniciens techniciensDTOToTechniciens(TechniciensDTO techniciensDTO);
}
