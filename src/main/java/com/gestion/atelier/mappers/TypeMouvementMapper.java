package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.TypeMouvementDTO;
import com.gestion.atelier.models.TypeMouvement;

@Mapper
public interface TypeMouvementMapper {
    TypeMouvementMapper INSTANCE = Mappers.getMapper(TypeMouvementMapper.class);

    TypeMouvementDTO typeMouvementToTypeMouvementDTO(TypeMouvement typeMouvement);

    TypeMouvement typeMouvementDTOToTypeMouvement(TypeMouvementDTO typeMouvementDTO);   
}
