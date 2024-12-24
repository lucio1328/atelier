package com.gestion.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Admins;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, Long> {

    @Query("SELECT a FROM Admins a LEFT JOIN FETCH a.genre")
    List<Admins> getAll();

    @Query("SELECT a FROM Admins a LEFT JOIN FETCH a.genre WHERE a.id = :id")
    Admins getById(Long id);
}
