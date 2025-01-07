package com.gestion.atelier.repository;

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

    @Query("SELECT c FROM Clients c JOIN FETCH c.genre where c.nom like %:nom% and c.prenom like %:prenom% ")
    List<Clients> getByNom(String nom, String prenom);
}
