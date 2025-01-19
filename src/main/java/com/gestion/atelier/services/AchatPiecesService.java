package com.gestion.atelier.services;

import com.gestion.atelier.DTO.MouvementsStockDTO;
import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.DTO.TypeMouvementDTO;
import com.gestion.atelier.models.MouvementsStock;
import com.gestion.atelier.models.PiecesDetachees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.AchatPiecesDTO;
import com.gestion.atelier.mappers.AchatPiecesMapper;
import com.gestion.atelier.repository.AchatPiecesRepository;
import com.gestion.atelier.models.AchatPieces;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchatPiecesService {

    @Autowired
    private AchatPiecesRepository achatPiecesRepository;

    @Autowired
    private MouvementsStockService mouvementsStockService;

    @Autowired
    private TypeMouvementService typeMouvementService;

    private final AchatPiecesMapper achatPiecesMapper = AchatPiecesMapper.INSTANCE;

    //
    public AchatPiecesDTO getById(Long id) {
        AchatPieces achatPieces = achatPiecesRepository.getById(id);
        return achatPiecesMapper.achatPiecesToAchatPiecesDTO(achatPieces);
    }

    // 
    public List<AchatPiecesDTO> getAll() {
        List<AchatPieces> achatPiecesList = achatPiecesRepository.getAll();
        return achatPiecesList.stream()
                              .map(achatPiecesMapper::achatPiecesToAchatPiecesDTO)
                              .collect(Collectors.toList());
    }

    // 
    public AchatPiecesDTO createAchatPiece(AchatPiecesDTO achatPiecesDTO) {
        AchatPieces achatPieces = achatPiecesMapper.achatPiecesDTOToAchatPieces(achatPiecesDTO);

        TypeMouvementDTO typeMouvementDTO = typeMouvementService.getTypeMouvementById(Long.parseLong("1"));

        AchatPieces savedAchatPieces = achatPiecesRepository.save(achatPieces);
        AchatPiecesDTO savedAchatDto = achatPiecesMapper.achatPiecesToAchatPiecesDTO(savedAchatPieces);

        MouvementsStockDTO mouvementsStockDTO = new MouvementsStockDTO(savedAchatPieces.getQuantite(), savedAchatPieces.getDateAchat(), savedAchatDto, null, typeMouvementDTO);
        mouvementsStockService.createMouvementStock(mouvementsStockDTO);

        return achatPiecesMapper.achatPiecesToAchatPiecesDTO(savedAchatPieces);
    }

    // 
    public AchatPiecesDTO updateAchatPiece(Long id, AchatPiecesDTO achatPiecesDTO) throws Exception {
        AchatPieces existingAchatPieces = achatPiecesRepository.getById(id);
        if (existingAchatPieces != null) {
            AchatPieces achatPiece = achatPiecesMapper.achatPiecesDTOToAchatPieces(achatPiecesDTO);

            AchatPieces updatedAchatPieces = achatPiecesRepository.save(achatPiece);
            return achatPiecesMapper.achatPiecesToAchatPiecesDTO(updatedAchatPieces);
        }
        throw new Exception("Achat de pièce introuvable pour la mise à jour");
    }

    //
    public void deleteAchatPiece(Long id) {
        achatPiecesRepository.deleteById(id);
    }

    public List<AchatPieces> getAchatsByPiece(Long pieceId) {
        return achatPiecesRepository.findByPieceDetachee(pieceId);
    }
}
