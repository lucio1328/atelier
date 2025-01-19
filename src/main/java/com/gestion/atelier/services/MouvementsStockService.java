package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.MouvementsStockDTO;
import com.gestion.atelier.mappers.MouvementsStockMapper;
import com.gestion.atelier.repository.MouvementsStockRepository;
import com.gestion.atelier.models.MouvementsStock;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MouvementsStockService {

    @Autowired
    private MouvementsStockRepository mouvementsStockRepository;

    private final MouvementsStockMapper mouvementsStockMapper = MouvementsStockMapper.INSTANCE;

    //
    public List<MouvementsStockDTO> getAllMouvementsStock() {
        List<MouvementsStock> mouvementsStock = mouvementsStockRepository.getAll();
        if (mouvementsStock == null || mouvementsStock.isEmpty()) {
            return List.of();
        }
        return mouvementsStock.stream()
                              .map(mouvementsStockMapper::mouvementsStockToMouvementsStockDTO)
                              .collect(Collectors.toList());
    }

    //
    public MouvementsStockDTO getMouvementStockById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du mouvement de stock ne peut pas être nul");
        }
        Optional<MouvementsStock> mouvementStockOpt = Optional.ofNullable(mouvementsStockRepository.getById(id));
        if (mouvementStockOpt.isEmpty()) {
            throw new IllegalArgumentException("Aucun mouvement de stock trouvé avec l'ID : " + id);
        }
        return mouvementsStockMapper.mouvementsStockToMouvementsStockDTO(mouvementStockOpt.get());
    }

    //
    public List<MouvementsStockDTO> getMouvementsStockBetweenDates(Date dateDebut, Date dateFin) {
        if (dateDebut == null || dateFin == null) {
            throw new IllegalArgumentException("Les dates de début et de fin ne peuvent pas être nulles");
        }
        List<MouvementsStock> mouvementsStock = mouvementsStockRepository.getBetweenDate(dateDebut, dateFin);
        if (mouvementsStock == null || mouvementsStock.isEmpty()) {
            return List.of();
        }
        return mouvementsStock.stream()
                              .map(mouvementsStockMapper::mouvementsStockToMouvementsStockDTO)
                              .collect(Collectors.toList());
    }

    //
    public List<MouvementsStockDTO> getMouvementsStockByReparation(Long reparationId) {
        if (reparationId == null) {
            throw new IllegalArgumentException("L'ID de réparation ne peut pas être nul");
        }
        List<MouvementsStock> mouvementsStock = mouvementsStockRepository.getByIdReparation(reparationId);
        if (mouvementsStock == null || mouvementsStock.isEmpty()) {
            return List.of();
        }
        return mouvementsStock.stream()
                              .map(mouvementsStockMapper::mouvementsStockToMouvementsStockDTO)
                              .collect(Collectors.toList());
    }

    //
    public List<MouvementsStockDTO> getMouvementsStockByAchatPiece(Long achatId) {
        if (achatId == null) {
            throw new IllegalArgumentException("L'ID d'achat de pièce ne peut pas être nul");
        }
        List<MouvementsStock> mouvementsStock = mouvementsStockRepository.getByAchatPiece(achatId);
        if (mouvementsStock == null || mouvementsStock.isEmpty()) {
            return List.of();
        }
        return mouvementsStock.stream()
                              .map(mouvementsStockMapper::mouvementsStockToMouvementsStockDTO)
                              .collect(Collectors.toList());
    }

    //
    public List<MouvementsStockDTO> getMouvementsStockByTypeMouvement(Long typeMouvementId) {
        if (typeMouvementId == null) {
            throw new IllegalArgumentException("L'ID du type de mouvement ne peut pas être nul");
        }
        List<MouvementsStock> mouvementsStock = mouvementsStockRepository.getByTypeMouvement(typeMouvementId);
        if (mouvementsStock == null || mouvementsStock.isEmpty()) {
            return List.of();
        }
        return mouvementsStock.stream()
                              .map(mouvementsStockMapper::mouvementsStockToMouvementsStockDTO)
                              .collect(Collectors.toList());
    }

    //
    public MouvementsStockDTO createMouvementStock(MouvementsStockDTO mouvementsStockDTO) {
        if (mouvementsStockDTO == null) {
            throw new IllegalArgumentException("Le DTO du mouvement de stock ne peut pas être nul");
        }

        MouvementsStock mouvementsStock = mouvementsStockMapper.mouvementsStockDTOToMouvementsStock(mouvementsStockDTO);
        if (mouvementsStock == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        MouvementsStock savedMouvementStock = mouvementsStockRepository.save(mouvementsStock);
        return mouvementsStockMapper.mouvementsStockToMouvementsStockDTO(savedMouvementStock);
    }

    //
    public MouvementsStockDTO updateMouvementStock(MouvementsStockDTO mouvementsStockDTO) {
        if (mouvementsStockDTO == null || mouvementsStockDTO.getId() == null) {
            throw new IllegalArgumentException("Le DTO ou l'ID du mouvement de stock ne peuvent pas être nuls");
        }
        Optional<MouvementsStock> existingMouvementStock = mouvementsStockRepository.findById(mouvementsStockDTO.getId());
        if (existingMouvementStock.isEmpty()) {
            throw new IllegalStateException("Aucun mouvement de stock trouvé pour l'ID spécifié");
        }

        MouvementsStock mouvementsStock = mouvementsStockMapper.mouvementsStockDTOToMouvementsStock(mouvementsStockDTO);
        if (mouvementsStock == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        MouvementsStock updatedMouvementStock = mouvementsStockRepository.save(mouvementsStock);
        return mouvementsStockMapper.mouvementsStockToMouvementsStockDTO(updatedMouvementStock);
    }

    //
    public void deleteMouvementStock(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du mouvement de stock ne peut pas être nul");
        }
        Optional<MouvementsStock> existingMouvementStock = mouvementsStockRepository.findById(id);
        if (existingMouvementStock.isEmpty()) {
            throw new IllegalStateException("Aucun mouvement de stock trouvé pour l'ID spécifié");
        }

        mouvementsStockRepository.delete(existingMouvementStock.get());
    }
}
