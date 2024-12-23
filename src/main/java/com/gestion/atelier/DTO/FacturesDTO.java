package com.gestion.atelier.DTO;

import java.util.Date;

public class FacturesDTO {

    private Long id;
    private Double montant;
    private Date date;
    private ReparationsDTO reparation;

    public FacturesDTO(Long id, Double montant, Date date, ReparationsDTO reparation) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.reparation = reparation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReparationsDTO getReparation() {
        return reparation;
    }

    public void setReparation(ReparationsDTO reparation) {
        this.reparation = reparation;
    }
}
