package com.gestion.atelier.DTO;

import java.sql.Date;

public class RetourDTO {

    private Long id;
    private Date dateRetour;
    private ReparationsDTO reparations;
   
    public RetourDTO(Long id, Date dateRetour, ReparationsDTO reparations) {
        this.id = id;
        this.dateRetour = dateRetour;
        this.reparations = reparations;
    }

    public RetourDTO() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDateRetour() {
        return dateRetour;
    }
    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
    public ReparationsDTO getReparations() {
        return reparations;
    }
    public void setReparations(ReparationsDTO reparations) {
        this.reparations = reparations;
    }
}

