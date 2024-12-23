package com.gestion.atelier.models;

import com.gestion.atelier.composite.SpecialiteTechnicienId;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(SpecialiteTechnicienId.class)
public class SpecialiteTechnicien {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_technicien")
    private Techniciens technicien;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_specialite")
    private Specialites specialite;

    public Techniciens getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Techniciens technicien) {
        this.technicien = technicien;
    }

    public Specialites getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialites specialite) {
        this.specialite = specialite;
    }
}
