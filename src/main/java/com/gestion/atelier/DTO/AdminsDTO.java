package com.gestion.atelier.DTO;

import java.util.Date;

public class AdminsDTO {

    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String email;
    private String motDePasse;
    private GenreDTO genre;
    
    public AdminsDTO() {
    }
    public AdminsDTO(Long id, String nom, String prenom, Date dateNaissance, String email, String motDePasse,
            GenreDTO genre) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.motDePasse = motDePasse;
        this.genre = genre;
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
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public GenreDTO getGenre() {
        return genre;
    }
    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

}

