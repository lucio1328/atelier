package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.AchatPiecesDTO;
import com.gestion.atelier.models.AchatPieces;

@Mapper
public interface AchatPiecesMapper {

    AchatPiecesMapper INSTANCE = Mappers.getMapper(AchatPiecesMapper.class);

    AchatPiecesDTO achatPiecesToAchatPiecesDTO(AchatPieces achatPieces);

    AchatPieces achatPiecesDTOToAchatPieces(AchatPiecesDTO achatPiecesDTO);
}

