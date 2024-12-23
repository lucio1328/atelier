package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.StatutDTO;
import com.gestion.atelier.models.Statut;


@Mapper
public interface StatutMapper {
    StatutMapper INSTANCE = Mappers.getMapper(StatutMapper.class);

    StatutDTO statutToStatutDTO(Statut statut);

    Statut statutDTOToStatut(StatutDTO statutDTO);
}
