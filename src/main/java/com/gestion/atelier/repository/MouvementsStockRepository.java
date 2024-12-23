package com.gestion.atelier.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.MouvementsStock;

@Repository
public interface MouvementsStockRepository extends JpaRepository<MouvementsStock, Long> {

    @Query("SELECT m FROM MouvementsStock m " +
           "LEFT JOIN FETCH m.reparation " +
           "LEFT JOIN FETCH m.achatPiece " +
           "LEFT JOIN FETCH m.typeMouvement " +
           "WHERE m.id = :id")
    MouvementsStock getById(Long id);

    @Query("SELECT m FROM MouvementsStock m " +
           "LEFT JOIN FETCH m.reparation " +
           "LEFT JOIN FETCH m.achatPiece " +
           "LEFT JOIN FETCH m.typeMouvement")
    List<MouvementsStock> getAll();

    @Query("SELECT m FROM MouvementsStock m " +
           "LEFT JOIN FETCH m.reparation " +
           "LEFT JOIN FETCH m.achatPiece " +
           "LEFT JOIN FETCH m.typeMouvement " +
           "WHERE m.dateMouvement BETWEEN :dateDebut AND :dateFin")
    List<MouvementsStock> getBetweenDate(Date dateDebut, Date dateFin);

    @Query("SELECT m FROM MouvementsStock m " +
           "LEFT JOIN FETCH m.reparation " +
           "LEFT JOIN FETCH m.achatPiece " +
           "LEFT JOIN FETCH m.typeMouvement " +
           "WHERE m.reparation.id = :reparationId")
    List<MouvementsStock> getByIdReparation(Long reparationId);

    @Query("SELECT m FROM MouvementsStock m " +
           "LEFT JOIN FETCH m.reparation " +
           "LEFT JOIN FETCH m.achatPiece " +
           "LEFT JOIN FETCH m.typeMouvement " +
           "WHERE m.achatPiece.id = :achatId")
    List<MouvementsStock> getByAchatPiece(Long achatId);

    @Query("SELECT m FROM MouvementsStock m " +
           "LEFT JOIN FETCH m.reparation " +
           "LEFT JOIN FETCH m.achatPiece " +
           "LEFT JOIN FETCH m.typeMouvement " +
           "WHERE m.typeMouvement.id = :typeMouvementId")
    List<MouvementsStock> getByTypeMouvement(Long typeMouvementId);

}
