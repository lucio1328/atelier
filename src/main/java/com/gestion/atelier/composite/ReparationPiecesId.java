package com.gestion.atelier.composite;

import java.io.Serializable;
import java.util.Objects;

public class ReparationPiecesId implements Serializable {

    private Long pieceDetachee;
    private Long reparation;

    public ReparationPiecesId() {}

    public Long getPieceDetachee() {
        return pieceDetachee;
    }

    public void setPieceDetachee(Long pieceDetachee) {
        this.pieceDetachee = pieceDetachee;
    }

    public Long getReparation() {
        return reparation;
    }

    public void setReparation(Long reparation) {
        this.reparation = reparation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReparationPiecesId that = (ReparationPiecesId) o;
        return Objects.equals(pieceDetachee, that.pieceDetachee) &&
               Objects.equals(reparation, that.reparation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceDetachee, reparation);
    }
}

