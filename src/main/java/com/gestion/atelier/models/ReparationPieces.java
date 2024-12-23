package com.gestion.atelier.models;

import com.gestion.atelier.composite.ReparationPiecesId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(ReparationPiecesId.class)
public class ReparationPieces {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_piece")
    private PiecesDetachees pieceDetachee;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reparation")
    private Reparations reparation;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    public PiecesDetachees getPieceDetachee() {
        return pieceDetachee;
    }

    public void setPieceDetachee(PiecesDetachees pieceDetachee) {
        this.pieceDetachee = pieceDetachee;
    }

    public Reparations getReparation() {
        return reparation;
    }

    public void setReparation(Reparations reparation) {
        this.reparation = reparation;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}
