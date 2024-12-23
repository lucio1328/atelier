package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Specialites;

@Repository
public interface SpecialitesRepository extends JpaRepository<Specialites, Long> {

    @Query("SELECT s FROM Specialites s WHERE s.id = :id")
    Specialites getById(Long id);

    @Query("SELECT s FROM Specialites s")
    List<Specialites> getAll();

    @Query("SELECT s FROM Specialites s WHERE s.libelle = :libelle")
    Specialites getByLibelle(String libelle);
}

