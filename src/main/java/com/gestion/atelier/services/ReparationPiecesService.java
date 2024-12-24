package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.DTO.ReparationPiecesDTO;
import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.mappers.ReparationPiecesMapper;
import com.gestion.atelier.repository.ReparationPiecesRepository;
import com.gestion.atelier.models.ReparationPieces;
import com.gestion.atelier.models.PiecesDetachees;
import com.gestion.atelier.models.Reparations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReparationPiecesService {

    @Autowired
    private ReparationPiecesRepository reparationPiecesRepository;

    private final ReparationPiecesMapper reparationPiecesMapper = ReparationPiecesMapper.INSTANCE;

    //
    public List<ReparationPiecesDTO> getPiecesByReparationId(Long reparationId) {
        if (reparationId == null) {
            throw new IllegalArgumentException("L'ID de la réparation ne peut pas être nul");
        }
        
        List<ReparationPieces> reparationPieces = reparationPiecesRepository.getPieceByIdReparation(reparationId);
        if (reparationPieces == null || reparationPieces.isEmpty()) {
            return List.of();
        }
        
        return reparationPieces.stream()
                               .map(reparationPiecesMapper::reparationPiecesToReparationPiecesDTO)
                               .collect(Collectors.toList());
    }

    //
    public ReparationPiecesDTO createReparationPiece(ReparationPiecesDTO reparationPiecesDTO) {
        if (reparationPiecesDTO == null) {
            throw new IllegalArgumentException("Le DTO de la pièce de réparation ne peut pas être nul");
        }

        PiecesDetacheesDTO pieceDetachee = reparationPiecesDTO.getPieceDetachee();
        ReparationsDTO reparation = reparationPiecesDTO.getReparation();

        if (pieceDetachee == null) {
            throw new IllegalArgumentException("La pièce détachée ne peut pas être nulle");
        }
        if (reparation == null) {
            throw new IllegalArgumentException("La réparation ne peut pas être nulle");
        }

        ReparationPieces reparationPiece = reparationPiecesMapper.reparationPiecesDTOToReparationPieces(reparationPiecesDTO);
        if (reparationPiece == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        ReparationPieces savedReparationPiece = reparationPiecesRepository.save(reparationPiece);
        return reparationPiecesMapper.reparationPiecesToReparationPiecesDTO(savedReparationPiece);
    }

    //
    // public ReparationPiecesDTO updateReparationPiece(ReparationPiecesDTO reparationPiecesDTO) {
    //     if (reparationPiecesDTO == null || reparationPiecesDTO.getPieceDetachee() == null || reparationPiecesDTO.getReparation() == null) {
    //         throw new IllegalArgumentException("Le DTO de la pièce de réparation ou ses entités liées ne peuvent pas être null");
    //     }

    //     
    //     Optional<ReparationPieces> existingReparationPiece = reparationPiecesRepository.findById(reparationPiecesDTO.getId());
    //     if (existingReparationPiece.isEmpty()) {
    //         throw new IllegalStateException("Aucune pièce de réparation trouvée pour l'ID spécifié");
    //     }
        
    //     ReparationPieces reparationPiece = reparationPiecesMapper.reparationPiecesDTOToReparationPieces(reparationPiecesDTO);
    //     if (reparationPiece == null) {
    //         throw new IllegalStateException("Erreur de conversion du DTO en entité");
    //     }

    //     ReparationPieces updatedReparationPiece = reparationPiecesRepository.save(reparationPiece);
    //     return reparationPiecesMapper.reparationPiecesToReparationPiecesDTO(updatedReparationPiece);
    // }

    // //
    // public void deleteReparationPiece(ReparationPiecesDTO reparationPiecesDTO) {
    //     if (reparationPiecesDTO == null || reparationPiecesDTO.getPieceDetachee() == null || reparationPiecesDTO.getReparation() == null) {
    //         throw new IllegalArgumentException("Le DTO de la pièce de réparation ou ses entités liées ne peuvent pas être null");
    //     }
    //     Optional<ReparationPieces> existingReparationPiece = reparationPiecesRepository.findById(reparationPiecesDTO.getId());
    //     if (existingReparationPiece.isEmpty()) {
    //         throw new IllegalStateException("Aucune pièce de réparation trouvée pour l'ID spécifié");
    //     }

    //     ReparationPieces reparationPiece = reparationPiecesMapper.reparationPiecesDTOToReparationPieces(reparationPiecesDTO);
    //     if (reparationPiece == null) {
    //         throw new IllegalStateException("Erreur de conversion du DTO en entité");
    //     }

    //     reparationPiecesRepository.delete(reparationPiece);
    // }
}
