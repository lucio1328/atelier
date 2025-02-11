package com.gestion.atelier.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Modeles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom_modele", nullable = false, length = 50)
    private String nomModele;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marque", nullable = false)
    private Marques marque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categories categorie;

    public Categories getCategorie() {
        return categorie;
    }

    public void setCategorie(Categories categorie) {
        this.categorie = categorie;
    }

    public Modeles(Long id, String nomModele, Marques marque) {
        this.id = id;
        this.nomModele = nomModele;
        this.marque = marque;
    }

    public Modeles() {
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

    public Marques getMarque() {
        return marque;
    }

    public void setMarque(Marques marque) {
        this.marque = marque;
    }

}
