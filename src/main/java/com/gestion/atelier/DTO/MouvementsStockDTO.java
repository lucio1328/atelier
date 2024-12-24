package com.gestion.atelier.DTO;

import java.util.Date;

public class MouvementsStockDTO {

    private Long id;
    private Integer quantite;
    private Date dateMouvement;
    private AchatPiecesDTO achatPiece;
    private ReparationsDTO reparation;
    private TypeMouvementDTO typeMouvement;

    public MouvementsStockDTO() {
    }

    public MouvementsStockDTO(Long id, Integer quantite, Date dateMouvement, AchatPiecesDTO achatPiece, ReparationsDTO reparation, TypeMouvementDTO typeMouvement) {
        this.id = id;
        this.quantite = quantite;
        this.dateMouvement = dateMouvement;
        this.achatPiece = achatPiece;
        this.reparation = reparation;
        this.typeMouvement = typeMouvement;
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

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Date dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public AchatPiecesDTO getAchatPiece() {
        return achatPiece;
    }

    public void setAchatPiece(AchatPiecesDTO achatPiece) {
        this.achatPiece = achatPiece;
    }

    public ReparationsDTO getReparation() {
        return reparation;
    }

    public void setReparation(ReparationsDTO reparation) {
        this.reparation = reparation;
    }

    public TypeMouvementDTO getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(TypeMouvementDTO typeMouvement) {
        this.typeMouvement = typeMouvement;
    }
}

