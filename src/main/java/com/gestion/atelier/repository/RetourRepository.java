package com.gestion.atelier.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Retour;

@Repository
public interface RetourRepository extends JpaRepository<Retour, Long> {

    @Query("SELECT r FROM Retour r LEFT JOIN FETCH r.reparations rep WHERE r.id = :id")
    Retour getById(Long id);

    @Query("SELECT r FROM Retour r LEFT JOIN FETCH r.reparations rep")
    List<Retour> getAll();

    @Query("SELECT r FROM Retour r " +
       "LEFT JOIN FETCH r.reparations rep " +
       "WHERE (:idCat is null or rep.ordinateur.modele.categorie.id = :idCat)" +
       "AND (:idType is null or rep.typeReparation.id = :idType)")
    List<Retour> getByCategorieTypeReparation(@Param("idCat") Long idCat, @Param("idType") Long idType);

}