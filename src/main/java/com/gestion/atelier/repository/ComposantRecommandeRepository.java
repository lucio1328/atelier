package com.gestion.atelier.repository;

import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.AchatPieces;
import com.gestion.atelier.models.ComposantRecommande;

@Repository
public interface ComposantRecommandeRepository extends JpaRepository<ComposantRecommande, Long> {
    @Query("SELECT a FROM ComposantRecommande a LEFT JOIN FETCH a.pieceDetachee p WHERE a.id = :id")
    ComposantRecommande getById(Long id);

    @Query("SELECT a FROM ComposantRecommande a LEFT JOIN FETCH a.pieceDetachee")
    List<ComposantRecommande> getAll();

    @Query("SELECT a FROM ComposantRecommande a LEFT JOIN FETCH a.pieceDetachee WHERE EXTRACT(MONTH FROM a.date) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM a.date) = EXTRACT(YEAR FROM CURRENT_DATE)")
    List<ComposantRecommande> getComposantRecommandeByDate();

    @Query("SELECT a FROM ComposantRecommande a LEFT JOIN FETCH a.pieceDetachee WHERE EXTRACT(MONTH FROM a.date) = :mois AND EXTRACT(YEAR FROM a.date) = :annee")
    List<ComposantRecommande> getComposantRecommandeByDate(Integer mois, Integer annee);

}
