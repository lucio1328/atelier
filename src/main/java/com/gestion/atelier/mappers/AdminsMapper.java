package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.AdminsDTO;
import com.gestion.atelier.models.Admins;

@Mapper
public interface AdminsMapper {
    AdminsMapper INSTANCE = Mappers.getMapper(AdminsMapper.class);

    AdminsDTO adminsToAdminsDTO(Admins admins);

    Admins adminsDTOToAdmins(AdminsDTO adminsDTO);
}
