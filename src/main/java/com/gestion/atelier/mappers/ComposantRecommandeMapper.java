package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.ComposantRecommandeDTO;
import com.gestion.atelier.models.ComposantRecommande;

@Mapper
public interface ComposantRecommandeMapper {
    ComposantRecommandeMapper INSTANCE = Mappers.getMapper(ComposantRecommandeMapper.class);

    ComposantRecommandeDTO composantRecommandeToComposantRecommandeDTO(ComposantRecommande composantRecommande);

    ComposantRecommande composantRecommandeDTOToComposantRecommande(ComposantRecommandeDTO composantRecommandeDTO);
}
