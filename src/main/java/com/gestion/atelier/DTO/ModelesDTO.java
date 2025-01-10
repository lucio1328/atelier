package com.gestion.atelier.DTO;

public class ModelesDTO {

    private Long id;
    private String nomModele;
    private MarquesDTO marque;
    private CategoriesDTO categorie;

    public ModelesDTO(Long id, String nomModele, MarquesDTO marque,CategoriesDTO categorie) {
        this.id = id;
        this.nomModele = nomModele;
        this.marque = marque;
        this.categorie = categorie;
    }
    public ModelesDTO() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomModele() {
        return nomModele;
    }
    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public MarquesDTO getMarque() {
        return marque;
    }
    public void setMarque(MarquesDTO marque) {
        this.marque = marque;
    }

    public CategoriesDTO getCategorie() {
        return categorie;
    }
    public void setCategorie(CategoriesDTO categorie) {
        this.categorie = categorie;
    }
}
