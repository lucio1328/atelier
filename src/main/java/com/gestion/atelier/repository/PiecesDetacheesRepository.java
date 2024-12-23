package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.PiecesDetachees;

@Repository
public interface PiecesDetacheesRepository extends JpaRepository<PiecesDetachees, Long> {

    @Query("SELECT p FROM PiecesDetachees p WHERE p.id = :id")
    PiecesDetachees getById(Long id);

    @Query("SELECT p FROM PiecesDetachees p")
    List<PiecesDetachees> getAll();

    @Query("SELECT p FROM PiecesDetachees p WHERE p.nomPiece LIKE %:nomPiece%")
    List<PiecesDetachees> getByNomPiece(String nomPiece);

    @Query("SELECT p FROM PiecesDetachees p WHERE p.reference LIKE %:reference%")
    List<PiecesDetachees> getByReference(String reference);

}
