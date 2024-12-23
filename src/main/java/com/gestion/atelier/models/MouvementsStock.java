package com.gestion.atelier.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MouvementsStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @Column(name = "date_mouvement", nullable = false)
    private Date dateMouvement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_achat")
    private AchatPieces achatPiece;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reparation")
    private Reparations reparation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_mouvement")
    private TypeMouvement typeMouvement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public AchatPieces getAchatPiece() {
        return achatPiece;
    }

    public void setAchatPiece(AchatPieces achatPiece) {
        this.achatPiece = achatPiece;
    }

    public Reparations getReparation() {
        return reparation;
    }

    public void setReparation(Reparations reparation) {
        this.reparation = reparation;
    }

    public TypeMouvement getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(TypeMouvement typeMouvement) {
        this.typeMouvement = typeMouvement;
    }
}
