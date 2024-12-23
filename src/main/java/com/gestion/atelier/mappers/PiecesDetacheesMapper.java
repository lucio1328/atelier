package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.models.PiecesDetachees;

@Mapper
public interface PiecesDetacheesMapper {

    PiecesDetacheesMapper INSTANCE = Mappers.getMapper(PiecesDetacheesMapper.class);

    PiecesDetacheesDTO piecesDetacheesToPiecesDetacheesDTO(PiecesDetachees piecesDetachees);

    PiecesDetachees piecesDetacheesDTOToPiecesDetachees(PiecesDetacheesDTO piecesDetacheesDTO);
}

