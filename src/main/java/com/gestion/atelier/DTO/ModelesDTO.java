package com.gestion.atelier.DTO;

public class ModelesDTO {

    private Long id;
    private String nomModele;
    private MarquesDTO marque;

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
}
