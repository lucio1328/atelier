package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Modeles;

@Repository
public interface ModelesRepository extends JpaRepository<Modeles, Long> {

    @Query("SELECT md FROM Modeles md JOIN FETCH md.marque JOIN FETCH md.categorie WHERE md.id = :id")
    Modeles getById(Long id);

    @Query("SELECT md FROM Modeles md JOIN FETCH md.marque JOIN FETCH md.categorie")
    List<Modeles> getAll();
}
