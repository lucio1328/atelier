package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.FacturesDTO;
import com.gestion.atelier.models.Factures;

@Mapper
public interface FacturesMapper {
    FacturesMapper INSTANCE = Mappers.getMapper(FacturesMapper.class);

    FacturesDTO facturesToFacturesDTO(Factures factures);

    Factures facturesDTOToFactures(FacturesDTO facturesDTO);
}
