package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Ordinateurs;

@Repository
public interface OrdinateursRepository extends JpaRepository<Ordinateurs, Long> {

    @Query("SELECT o FROM Ordinateur o " +
           "JOIN FETCH o.modele m " +
           "JOIN FETCH o.client c " +
           "WHERE o.id = :id")
    Ordinateurs getById(Long id);

    @Query("SELECT o FROM Ordinateur o " +
           "JOIN FETCH o.modele m " + 
           "JOIN FETCH o.client c")
    List<Ordinateurs> getAll();
}
