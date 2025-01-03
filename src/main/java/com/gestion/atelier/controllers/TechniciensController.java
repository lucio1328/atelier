package com.gestion.atelier.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.atelier.DTO.GenreDTO;
import com.gestion.atelier.DTO.TechniciensDTO;
import com.gestion.atelier.services.GenreService;
import com.gestion.atelier.services.TechniciensService;

@Controller
@RequestMapping("/techniciens")
public class TechniciensController {
    @Autowired
    private TechniciensService techniciensService;

    @Autowired
    private GenreService genreService;

    // Afficher la liste des techniciens
    @GetMapping("/liste")
    public ModelAndView getAllTechniciens() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<TechniciensDTO> techniciens = techniciensService.getAll();

        modelAndView.addObject("view", "techniciens/liste.jsp");
        modelAndView.addObject("techniciens", techniciens);
        return modelAndView;
    }

    // Afficher le formulaire de création d'un technicien
    @GetMapping("/create")
    public ModelAndView createTechnicienForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<GenreDTO> genres = genreService.getAll();

        modelAndView.addObject("view", "techniciens/form.jsp");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("genres", genres);
        return modelAndView;
    }

    // Créer un nouveau technicien
    @PostMapping("/create")
    public ModelAndView createTechnicien(@RequestParam("nom") String nom,
                                    @RequestParam("prenom") String prenom,
                                    @RequestParam("dateNaissance") String dateNaissance,
                                    @RequestParam("email") String email,
                                    @RequestParam("motDePasse") String motDePasse,
                                    @RequestParam("genre") String genreId) {
        TechniciensDTO techniciensDTO = new TechniciensDTO();
        try {
            if (genreId != null && !genreId.isEmpty() && nom != null && prenom != null && dateNaissance != null && email != null && motDePasse != null) {
                Long id = Long.parseLong(genreId);
                GenreDTO selectedGenre = genreService.getById(id);

                Date date = Date.valueOf(dateNaissance);

                techniciensDTO.setNom(nom);
                techniciensDTO.setPrenom(prenom);
                techniciensDTO.setDateNaissance(date);
                techniciensDTO.setEmail(email);
                techniciensDTO.setMotDePasse(motDePasse);
                techniciensDTO.setGenre(selectedGenre);
            }

            techniciensService.createTechnicien(techniciensDTO);
            return new ModelAndView("redirect:/techniciens/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "techniciens/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("technicien", techniciensDTO);
            mav.addObject("action", "create");
            return mav;
        }
    }


    // Afficher le formulaire de modification d'un technicien
    @GetMapping("/edit/{id}")
    public ModelAndView editTechnicienForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            TechniciensDTO client = techniciensService.getById(id);
            List<GenreDTO> genre = genreService.getAll();

            modelAndView.addObject("view", "techniciens/form.jsp");
            modelAndView.addObject("genres", genre);
            modelAndView.addObject("client", client);
            return modelAndView;
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView();

            mav.setViewName("redirect:/techniciens/liste"); 
            mav.addObject("view", "error");
            mav.addObject("message", "Genre introuvable");        
            return mav;
        }
    }

    // Modifier un technicien existant
    @PostMapping("/edit/{id}")
    public ModelAndView editClient(@PathVariable Long id, 
                                @RequestParam("nom") String nom,
                                @RequestParam("prenom") String prenom,
                                @RequestParam("dateNaissance") String dateNaissance,
                                @RequestParam("email") String email,
                                @RequestParam("motDePasse") String motDePasse,
                                @RequestParam("genre") String genreId) throws Exception {
        TechniciensDTO technicienExist = new TechniciensDTO();
        if (id != null) {
            technicienExist = techniciensService.getById(id);
        }
                                    
        try {
            if (genreId != null && nom != null && prenom != null && dateNaissance != null && email != null && motDePasse != null) {
                Long idGenre = Long.parseLong(genreId);
                GenreDTO selectedGenre = genreService.getById(idGenre);

                Date date = Date.valueOf(dateNaissance);

                technicienExist.setNom(nom);
                technicienExist.setPrenom(prenom);
                technicienExist.setDateNaissance(date);
                technicienExist.setEmail(email);
                technicienExist.setMotDePasse(motDePasse);
                technicienExist.setGenre(selectedGenre);
            }

            techniciensService.updateTechnicien(id, technicienExist);
            return new ModelAndView("redirect:/techniciens/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "techniciens/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("technicien", technicienExist);
            mav.addObject("action", "edit");
            return mav;
        }
    }

    // Supprimer un technicien
    @GetMapping("/delete/{id}")
    public ModelAndView deleteTechnicien(@PathVariable Long id) {
        try {
            techniciensService.deleteTechnicien(id);
            return new ModelAndView("redirect:/techniciens/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }
}
