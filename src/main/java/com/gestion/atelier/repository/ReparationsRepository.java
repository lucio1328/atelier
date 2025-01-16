package com.gestion.atelier.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion.atelier.models.Reparations;
import com.gestion.atelier.models.TypeReparation;

@Repository
public interface ReparationsRepository extends JpaRepository<Reparations, Long> {

    @Query("SELECT r FROM Reparations r LEFT JOIN FETCH r.technicien t LEFT JOIN FETCH r.client c LEFT JOIN FETCH r.ordinateur ordi LEFT JOIN FETCH r.statut s LEFT JOIN FETCH r.typeReparation rep WHERE r.id = :id")
    Reparations getById(Long id);

    @Query("SELECT r FROM Reparations r LEFT JOIN FETCH r.technicien t LEFT JOIN FETCH r.client c LEFT JOIN FETCH r.ordinateur ordi LEFT JOIN FETCH r.statut s LEFT JOIN FETCH r.typeReparation rep")
    List<Reparations> getAll();

    @Query("SELECT r FROM Reparations r LEFT JOIN FETCH r.technicien t LEFT JOIN FETCH r.client c LEFT JOIN FETCH r.ordinateur ordi LEFT JOIN FETCH r.statut s LEFT JOIN FETCH r.typeReparation rep WHERE r.statut.id = :statutId")
    List<Reparations> getByIdStatut(Long statutId);

    @Query("SELECT r FROM Reparations r LEFT JOIN FETCH r.technicien t LEFT JOIN FETCH r.client c LEFT JOIN FETCH r.ordinateur ordi LEFT JOIN FETCH r.statut s LEFT JOIN FETCH r.typeReparation rep WHERE r.technicien.id = :technicienId")
    List<Reparations> getByIdTechnicien(Long technicienId);

    @Query("SELECT r FROM Reparations r LEFT JOIN FETCH r.technicien t LEFT JOIN FETCH r.client c LEFT JOIN FETCH r.ordinateur ordi LEFT JOIN FETCH r.statut s LEFT JOIN FETCH r.typeReparation rep WHERE r.client.id = :clientId")
    List<Reparations> getByIdClient(Long clientId);

    @Query("SELECT r FROM Reparations r LEFT JOIN FETCH r.technicien t LEFT JOIN FETCH r.client c LEFT JOIN FETCH r.ordinateur ordi LEFT JOIN FETCH r.statut s LEFT JOIN FETCH r.typeReparation rep WHERE r.dateDebut >= :dateDebut AND r.dateFin <= :dateFin")
    List<Reparations> getBetweenDate(Date dateDebut, Date dateFin);

    List<Reparations> findByTypeReparation(TypeReparation type);

    @Query("SELECT r FROM Reparations r LEFT JOIN FETCH r.technicien t LEFT JOIN FETCH r.client c LEFT JOIN FETCH r.ordinateur ordi LEFT JOIN FETCH r.statut s LEFT JOIN FETCH r.typeReparation rep WHERE r.dateDebut = CURRENT_DATE")
    List<Reparations> getByDate();

    @Query("SELECT r FROM Reparations r LEFT JOIN FETCH r.technicien t LEFT JOIN FETCH r.client c LEFT JOIN FETCH r.ordinateur ordi LEFT JOIN FETCH r.statut s LEFT JOIN FETCH r.typeReparation rep WHERE r.dateDebut = :date")
    List<Reparations> getByDate(Date date);
}
