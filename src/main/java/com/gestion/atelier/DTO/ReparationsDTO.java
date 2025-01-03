package com.gestion.atelier.DTO;

import java.sql.Date;

public class ReparationsDTO {

    private Long id;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private TechniciensDTO technicien;
    private ClientsDTO client;
    private OrdinateursDTO ordinateur;
    private StatutDTO statut;
    
    public ReparationsDTO(Long id, String description, Date dateDebut, Date dateFin, TechniciensDTO technicien,
            ClientsDTO client, OrdinateursDTO ordinateur, StatutDTO statut) {
        this.id = id;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.technicien = technicien;
        this.client = client;
        this.ordinateur = ordinateur;
        this.statut = statut;
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

}

