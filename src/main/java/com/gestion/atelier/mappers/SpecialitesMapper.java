package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.SpecialitesDTO;
import com.gestion.atelier.models.Specialites;

@Mapper
public interface SpecialitesMapper {
    SpecialitesMapper INSTANCE = Mappers.getMapper(SpecialitesMapper.class);

    SpecialitesDTO specialitesToSpecialitesDTO(Specialites specialites);

    Specialites specialitesDTOToSpecialites(SpecialitesDTO specialitesDTO);
}
