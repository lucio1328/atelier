package com.gestion.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.TypeReparation;

@Repository
public interface TypeReparationRepository extends JpaRepository<TypeReparation, Long>  {
    @Query("SELECT tr FROM TypeReparation tr WHERE tr.id = :id")
    TypeReparation getTypeReparationById(@Param("id") Long id);
}