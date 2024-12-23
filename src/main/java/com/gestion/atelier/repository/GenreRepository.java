package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    
    @Query("SELECT g FROM Genre g WHERE g.id = :id")
    Genre getById(Long id);
    
    @Query("SELECT g FROM Genre g")
    List<Genre> getAll();

}
