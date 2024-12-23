package com.gestion.atelier.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Factures;

@Repository
public interface FacturesRepository extends JpaRepository<Factures, Long> {

    @Query("SELECT f FROM Factures f JOIN FETCH f.reparation WHERE f.id = :id")
    Factures getById(Long id);

    @Query("SELECT f FROM Factures f JOIN FETCH f.reparation")
    List<Factures> getAll();

}
