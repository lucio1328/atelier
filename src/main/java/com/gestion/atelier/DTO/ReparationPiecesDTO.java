package com.gestion.atelier.DTO;

public class ReparationPiecesDTO {

    private PiecesDetacheesDTO pieceDetachee;
    private ReparationsDTO reparation;
    private Integer quantite;

    public ReparationPiecesDTO() {
    }

    public ReparationPiecesDTO(PiecesDetacheesDTO pieceDetachee, ReparationsDTO reparation, Integer quantite) {
        this.pieceDetachee = pieceDetachee;
        this.reparation = reparation;
        this.quantite = quantite;
    }

    public PiecesDetacheesDTO getPieceDetachee() {
        return pieceDetachee;
    }

    public void setPieceDetachee(PiecesDetacheesDTO pieceDetachee) {
        this.pieceDetachee = pieceDetachee;
    }

    public ReparationsDTO getReparation() {
        return reparation;
    }

    public void setReparation(ReparationsDTO reparation) {
        this.reparation = reparation;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}

