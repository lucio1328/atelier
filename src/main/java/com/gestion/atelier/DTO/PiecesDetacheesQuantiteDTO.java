package com.gestion.atelier.DTO;

public class PiecesDetacheesQuantiteDTO {
    private Long id;
    private String nom;
    private Integer quantite;

    public PiecesDetacheesQuantiteDTO(Long id, String nom, Integer quantite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}
