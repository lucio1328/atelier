package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.AchatPieces;

@Repository
public interface AchatPiecesRepository extends JpaRepository<AchatPieces, Long> {

    @Query("SELECT a FROM AchatPieces a LEFT JOIN FETCH a.pieceDetachee p WHERE a.id = :id")
    AchatPieces getById(Long id);

    @Query("SELECT a FROM AchatPieces a LEFT JOIN FETCH a.pieceDetachee")
    List<AchatPieces> getAll();

    @Query("SELECT a FROM AchatPieces a LEFT JOIN FETCH a.pieceDetachee p WHERE p.id = :pieceDetachee and a.quantite_disponible > 0")
    List<AchatPieces> findByPieceDetachee(Long pieceDetachee);
}
