package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.OrdinateursDTO;
import com.gestion.atelier.models.Ordinateurs;

@Mapper
public interface OrdinateursMapper {
    OrdinateursMapper INSTANCE = Mappers.getMapper(OrdinateursMapper.class);

    OrdinateursDTO ordinateursToOrdinateursDTO(Ordinateurs ordinateurs);

    Ordinateurs ordinateursDTOToOrdinateurs(OrdinateursDTO ordinateursDTO);
}
