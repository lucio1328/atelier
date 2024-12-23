package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.SpecialiteTechnicienDTO;
import com.gestion.atelier.models.SpecialiteTechnicien;

@Mapper
public interface SpecialiteTechnicienMapper {
    SpecialiteTechnicienMapper INSTANCE = Mappers.getMapper(SpecialiteTechnicienMapper.class);

    SpecialiteTechnicienDTO specialiteTechnicienToSpecialiteTechnicienDTO(SpecialiteTechnicien specialiteTechnicien);

    SpecialiteTechnicien specialiteTechnicienDTOToSpecialiteTechnicien(SpecialiteTechnicienDTO specialiteTechnicienDTO);
}
