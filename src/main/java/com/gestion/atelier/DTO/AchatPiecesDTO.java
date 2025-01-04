package com.gestion.atelier.DTO;

import java.sql.Date;

public class AchatPiecesDTO {

    private Long id;
    private Integer quantite;
    private Integer quantiteDisponible;
    private Double prixUnitaire;
    private Date dateAchat;
    private PiecesDetacheesDTO pieceDetachee;

    public AchatPiecesDTO() {
    }

    public AchatPiecesDTO(Long id, Integer quantite, Integer quantiteDisponible, Double prixUnitaire, Date dateAchat, PiecesDetacheesDTO pieceDetachee) {
        this.id = id;
        this.quantite = quantite;
        this.quantiteDisponible = quantiteDisponible;
        this.prixUnitaire = prixUnitaire;
        this.dateAchat = dateAchat;
        this.pieceDetachee = pieceDetachee;
    }

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

    public Integer getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(Integer quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public PiecesDetacheesDTO getPieceDetachee() {
        return pieceDetachee;
    }

    public void setPieceDetachee(PiecesDetacheesDTO pieceDetachee) {
        this.pieceDetachee = pieceDetachee;
    }
}

