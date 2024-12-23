package com.gestion.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Statut;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {
   
}
