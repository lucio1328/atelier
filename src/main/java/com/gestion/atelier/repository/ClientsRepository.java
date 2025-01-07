package com.gestion.atelier.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {

    @Query("SELECT c FROM Clients c JOIN FETCH c.genre WHERE c.id = :id")
    Clients getById(Long id);

    @Query("SELECT c FROM Clients c JOIN FETCH c.genre")
    List<Clients> getAll();

    @Query("SELECT c FROM Clients c JOIN FETCH c.genre " +
            "WHERE (:nom IS NULL OR c.nom LIKE %:nom%) " +
            "AND (:prenom IS NULL OR c.prenom LIKE %:prenom%) " +
            "AND (:dateDebut IS NULL OR :dateFin IS NULL OR c.dateNaissance BETWEEN :dateDebut AND :dateFin)")
    List<Clients> searchClients(String nom, String prenom, Date dateDebut, Date dateFin);
}
