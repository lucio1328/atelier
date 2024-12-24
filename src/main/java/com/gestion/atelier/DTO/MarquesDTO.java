package com.gestion.atelier.DTO;

public class MarquesDTO {
    private Long id;
    private String nomMarque;

    public MarquesDTO() {
    }
    public MarquesDTO(Long id, String nomMarque) {
        this.id = id;
        this.nomMarque = nomMarque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }
}
