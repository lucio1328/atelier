package com.gestion.atelier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion.atelier.models.TypeMouvement;

@Repository
public interface TypeMouvementRepository extends JpaRepository<TypeMouvement, Long> {
    
}
