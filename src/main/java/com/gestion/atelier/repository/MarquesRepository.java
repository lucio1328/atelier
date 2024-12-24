package com.gestion.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Marques;

@Repository
public interface MarquesRepository extends JpaRepository<Marques, Long> {
   
}
