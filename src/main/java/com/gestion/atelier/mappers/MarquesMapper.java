package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.MarquesDTO;
import com.gestion.atelier.models.Marques;

@Mapper
public interface MarquesMapper {
    MarquesMapper INSTANCE = Mappers.getMapper(MarquesMapper.class);

    MarquesDTO marquesToMarquesDTO(Marques marques);

    Marques marquesDTOToMarques(MarquesDTO marquesDTO);
}
