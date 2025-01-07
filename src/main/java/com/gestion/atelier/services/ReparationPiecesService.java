package com.gestion.atelier.services;

import com.gestion.atelier.models.*;
import com.gestion.atelier.repository.AchatPiecesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.DTO.ReparationPiecesDTO;
import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.DTO.SpecialiteTechnicienDTO;
import com.gestion.atelier.DTO.SpecialitesDTO;
import com.gestion.atelier.DTO.TechniciensDTO;
import com.gestion.atelier.mappers.PiecesDetacheesMapper;
import com.gestion.atelier.mappers.ReparationPiecesMapper;
import com.gestion.atelier.mappers.ReparationsMapper;
import com.gestion.atelier.repository.PiecesDetacheesRepository;
import com.gestion.atelier.repository.ReparationPiecesRepository;
import com.gestion.atelier.repository.ReparationsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReparationPiecesService {

    @Autowired
    private ReparationPiecesRepository reparationPiecesRepository;

    @Autowired
    private ReparationsRepository reparationsRepository;

    @Autowired
    private PiecesDetacheesRepository piecesDetacheesRepository;
    @Autowired
    private AchatPiecesRepository achatPiecesRepository;

    private final ReparationPiecesMapper reparationPiecesMapper = ReparationPiecesMapper.INSTANCE;
    private final ReparationsMapper reparationsMapper = ReparationsMapper.INSTANCE;
    private final PiecesDetacheesMapper piecesDetacheesMapper = PiecesDetacheesMapper.INSTANCE;

    //
    public Map<PiecesDetachees, Integer> getPiecesByReparationId(Long reparationId) {
        Map<PiecesDetachees, Integer> piecesQuantite = new HashMap<>();
        if (reparationId == null) {
            throw new IllegalArgumentException("L'ID de la réparation ne peut pas être nul");
        }
        
        List<ReparationPieces> reparationPieces = reparationPiecesRepository.getPieceByIdReparation(reparationId);
        if (reparationPieces == null || reparationPieces.isEmpty()) {
            return piecesQuantite;
        }

        // List<PiecesDetachees> piecesDetachees = new ArrayList<>();
        for(ReparationPieces rPieces : reparationPieces) {
            piecesQuantite.putIfAbsent(rPieces.getPieceDetachee(), rPieces.getQuantite());
            // piecesDetachees.add(rPieces.getPieceDetachee());
        }
        
        return piecesQuantite;
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
    public void save(Long reparationId, Long pieceId, Integer quantite) throws Exception {
        List<AchatPieces> achatsDisponibles = achatPiecesRepository.findByPieceDetachee(pieceId);

        int totalQuantiteDisponible = achatsDisponibles.stream()
                .mapToInt(AchatPieces::getQuantiteDisponible)
                .sum();

        if (quantite > totalQuantiteDisponible) {
            throw new Exception("Quantité insuffisante pour cette pièce.");
        }

        int quantiteRestante = quantite;
        for (AchatPieces achat : achatsDisponibles) {
            if (quantiteRestante == 0) break;

            int quantiteUtilisee = Math.min(achat.getQuantiteDisponible(), quantiteRestante);
            achat.setQuantiteDisponible(achat.getQuantiteDisponible() - quantiteUtilisee);
            quantiteRestante -= quantiteUtilisee;
            achatPiecesRepository.save(achat);
        }

        Reparations reparation = reparationsRepository.findById(reparationId)
                .orElseThrow(() -> new Exception("Réparation introuvable."));
        ReparationPieces reparationPiece = new ReparationPieces();
        reparationPiece.setReparation(reparation);
        reparationPiece.setPieceDetachee(piecesDetacheesRepository.getById(pieceId));
        reparationPiece.setQuantite(quantite);

        reparationPiecesRepository.save(reparationPiece);
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
