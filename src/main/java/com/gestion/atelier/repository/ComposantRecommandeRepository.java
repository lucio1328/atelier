package com.gestion.atelier.repository;

import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT a FROM ComposantRecommande a LEFT JOIN FETCH a.pieceDetachee WHERE "
            + "(:mois IS NULL OR EXTRACT(MONTH FROM a.date) = :mois) "
            + "AND (:annee IS NULL OR EXTRACT(YEAR FROM a.date) = :annee)")
    List<ComposantRecommande> getComposantRecommandeByDate(@Param("mois") String mois, @Param("annee") String annee);

    @Query("SELECT a FROM ComposantRecommande a LEFT JOIN FETCH a.pieceDetachee WHERE (EXTRACT(MONTH FROM a.date) = :mois)")
    List<ComposantRecommande> getComposantRecommandeByMois(String mois);

    @Query("SELECT a FROM ComposantRecommande a LEFT JOIN FETCH a.pieceDetachee WHERE (EXTRACT(YEAR FROM a.date) = :annee)")
    List<ComposantRecommande> getComposantRecommandeByAnnee(String annee);

}