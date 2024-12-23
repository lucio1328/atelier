package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.ReparationPiecesDTO;
import com.gestion.atelier.models.ReparationPieces;

@Mapper
public interface ReparationPiecesMapper {
    ReparationPiecesMapper INSTANCE = Mappers.getMapper(ReparationPiecesMapper.class);

    ReparationPiecesDTO reparationPiecesToReparationPiecesDTO(ReparationPieces reparationPieces);

    ReparationPieces reparationPiecesDTOToReparationPieces(ReparationPiecesDTO reparationPiecesDTO);
}
