package com.gestion.atelier.DTO;

public class CategoriesDTO {

    private Long id;
    private String libelle;
    
    public CategoriesDTO() {
    }

    public CategoriesDTO(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

