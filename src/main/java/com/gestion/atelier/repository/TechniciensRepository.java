package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Techniciens;

@Repository
public interface TechniciensRepository extends JpaRepository<Techniciens, Long> {

    @Query("SELECT t FROM Techniciens t LEFT JOIN FETCH t.genre")
    List<Techniciens> getAll();

    @Query("SELECT t FROM Techniciens t LEFT JOIN FETCH t.genre WHERE t.id = :id")
    Techniciens getById(Long id);
}
