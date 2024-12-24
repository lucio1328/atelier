package com.gestion.atelier.DTO;

public class OrdinateursDTO {

    private Long id;
    private String numeroSerie;
    private String description;
    private ClientsDTO client;
    private ModelesDTO modele;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClientsDTO getClient() {
        return client;
    }

    public void setClient(ClientsDTO client) {
        this.client = client;
    }

    public ModelesDTO getModele() {
        return modele;
    }

    public void setModele(ModelesDTO modele) {
        this.modele = modele;
    }
}
