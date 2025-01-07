package com.gestion.atelier.DTO;

import java.sql.Date;

public class ReparationsDTO {

    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private ClientsDTO client;
    private StatutDTO statut;
    private TechniciensDTO technicien;
    private OrdinateursDTO ordinateur;
    private TypeReparationDTO typeReparation;
   
    public ReparationsDTO(Long id, Date dateDebut, Date dateFin, String description, ClientsDTO client,
            StatutDTO statut, TechniciensDTO technicien, OrdinateursDTO ordinateur , TypeReparationDTO typeReparation) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.client = client;
        this.statut = statut;
        this.technicien = technicien;
        this.ordinateur = ordinateur;
        this.typeReparation = typeReparation;
    }

    public ReparationsDTO() {
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
    public TechniciensDTO getTechnicien() {
        return technicien;
    }
    public void setTechnicien(TechniciensDTO technicien) {
        this.technicien = technicien;
    }
    public ClientsDTO getClient() {
        return client;
    }
    public void setClient(ClientsDTO client) {
        this.client = client;
    }
    public OrdinateursDTO getOrdinateur() {
        return ordinateur;
    }
    public void setOrdinateur(OrdinateursDTO ordinateur) {
        this.ordinateur = ordinateur;
    }
    public StatutDTO getStatut() {
        return statut;
    }
    public void setStatut(StatutDTO statut) {
        this.statut = statut;
    }

    public TypeReparationDTO getTypeReparation() {
        return typeReparation;
    }

    public void setTypeReparation(TypeReparationDTO typeReparationDTO) {
        this.typeReparation = typeReparationDTO;
    }
    

}

