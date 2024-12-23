package com.gestion.atelier.composite;

import java.io.Serializable;
import java.util.Objects;

public class SpecialiteTechnicienId implements Serializable {

    private Long technicien;
    private Long specialite;

    public SpecialiteTechnicienId() {}

    public SpecialiteTechnicienId(Long technicien, Long specialite) {
        this.technicien = technicien;
        this.specialite = specialite;
    }

    public Long getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Long technicien) {
        this.technicien = technicien;
    }

    public Long getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Long specialite) {
        this.specialite = specialite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialiteTechnicienId that = (SpecialiteTechnicienId) o;
        return Objects.equals(technicien, that.technicien) && Objects.equals(specialite, that.specialite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(technicien, specialite);
    }
}

