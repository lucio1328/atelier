package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.RetourDTO;
import com.gestion.atelier.models.Retour;

@Mapper
public interface RetourMapper {
    RetourMapper INSTANCE = Mappers.getMapper(RetourMapper.class);

    RetourDTO retourToRetourDTO(Retour retour);

    Retour retourDTOToRetour(RetourDTO retourDTO);
}
