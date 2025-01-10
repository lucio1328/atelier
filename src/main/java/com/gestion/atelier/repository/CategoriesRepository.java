package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Query("SELECT s FROM Categories s WHERE s.id = :id")
    Categories getById(Long id);

    @Query("SELECT s FROM Categories s")
    List<Categories> getAll();

    @Query("SELECT s FROM Categories s WHERE s.libelle = :libelle")
    Categories getByLibelle(String libelle);
}

