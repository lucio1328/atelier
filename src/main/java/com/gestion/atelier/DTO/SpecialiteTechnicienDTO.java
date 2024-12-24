package com.gestion.atelier.DTO;

public class SpecialiteTechnicienDTO {

    private TechniciensDTO technicien;
    private SpecialitesDTO specialite;

    public SpecialiteTechnicienDTO() {
    }

    public SpecialiteTechnicienDTO(TechniciensDTO technicien, SpecialitesDTO specialite) {
        this.technicien = technicien;
        this.specialite = specialite;
    }

    public TechniciensDTO getTechnicien() {
        return technicien;
    }

    public void setTechnicien(TechniciensDTO technicien) {
        this.technicien = technicien;
    }

    public SpecialitesDTO getSpecialite() {
        return specialite;
    }

    public void setSpecialite(SpecialitesDTO specialite) {
        this.specialite = specialite;
    }
}

