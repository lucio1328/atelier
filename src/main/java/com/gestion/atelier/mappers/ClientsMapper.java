package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.ClientsDTO;
import com.gestion.atelier.models.Clients;

@Mapper
public interface ClientsMapper {

    ClientsMapper INSTANCE = Mappers.getMapper(ClientsMapper.class);

    ClientsDTO clientsToClientsDTO(Clients clients);

    Clients clientsDTOToClients(ClientsDTO clientsDTO);
}

