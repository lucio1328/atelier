package com.gestion.atelier.DTO;

public class PiecesDetacheesDTO {

    private Long id;
    private String nomPiece;
    private String reference;
    private String description;

    public PiecesDetacheesDTO() {
    }

    public PiecesDetacheesDTO(Long id, String nomPiece, String reference, String description) {
        this.id = id;
        this.nomPiece = nomPiece;
        this.reference = reference;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPiece() {
        return nomPiece;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
