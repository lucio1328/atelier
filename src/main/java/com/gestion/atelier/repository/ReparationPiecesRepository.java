package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.composite.ReparationPiecesId;
import com.gestion.atelier.models.ReparationPieces;

@Repository
public interface ReparationPiecesRepository extends JpaRepository<ReparationPieces, ReparationPiecesId> {

    @Query("SELECT rp FROM ReparationPieces rp LEFT JOIN FETCH rp.reparation r LEFT JOIN FETCH rp.pieceDetachee p WHERE rp.reparation.id = :reparationId")
    List<ReparationPieces> getPieceByIdReparation(Long reparationId);
}
