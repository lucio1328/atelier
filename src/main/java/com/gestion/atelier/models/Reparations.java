package com.gestion.atelier.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reparations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_debut", nullable = false)
    private Date dateDebut;

    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "commission", nullable = false)
    private Double commission;

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Clients client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_statut")
    private Statut statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_technicien")
    private Techniciens technicien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ordinateur")
    private Ordinateurs ordinateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_reparation")
    private TypeReparation typeReparation;

    public TypeReparation getTypeReparation() {
        return typeReparation;
    }

    public void setTypeReparation(TypeReparation typeReparation) {
        this.typeReparation = typeReparation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Techniciens getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Techniciens technicien) {
        this.technicien = technicien;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Ordinateurs getOrdinateur() {
        return ordinateur;
    }
    public void setOrdinateur(Ordinateurs ordinateur) {
        this.ordinateur = ordinateur;
    }

}
