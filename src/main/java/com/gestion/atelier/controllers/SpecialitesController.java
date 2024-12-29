package com.gestion.atelier.controllers;

import com.gestion.atelier.DTO.SpecialitesDTO;
import com.gestion.atelier.services.SpecialitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/specialites")
public class SpecialitesController {

    @Autowired
    private SpecialitesService specialitesService;

    // Afficher la liste des spécialités
    @GetMapping("/liste")
    public ModelAndView getAllSpecialites() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<SpecialitesDTO> specialites = specialitesService.getAllSpecialites();

        modelAndView.addObject("view", "specialites/liste.jsp");
        modelAndView.addObject("specialites", specialites);
        return modelAndView;
    }

    // Afficher le formulaire de création d'une spécialité
    @GetMapping("/create")
    public ModelAndView createSpecialiteForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        modelAndView.addObject("view", "specialites/form.jsp");
        modelAndView.addObject("action", "create");
        return modelAndView;
    }

    // Créer une nouvelle spécialité
    @PostMapping("/create")
    public ModelAndView createSpecialite(@RequestParam("libelle") String libelle) {
        SpecialitesDTO specialitesDTO = new SpecialitesDTO();
        try {
            if (libelle != null && !libelle.trim().isEmpty()) {
                specialitesDTO.setLibelle(libelle);
            }

            specialitesService.createSpecialite(specialitesDTO);
            return new ModelAndView("redirect:/specialites/liste");
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "specialites/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("specialite", specialitesDTO);
            mav.addObject("action", "create");
            return mav;
        }
    }

    // Afficher le formulaire de modification d'une spécialité
    @GetMapping("/edit/{id}")
    public ModelAndView editSpecialiteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            SpecialitesDTO specialite = specialitesService.getSpecialiteById(id);

            modelAndView.addObject("view", "specialites/form.jsp");
            modelAndView.addObject("specialite", specialite);
            modelAndView.addObject("action", "edit");
            return modelAndView;
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/specialites/liste");
            mav.addObject("message", "Spécialité introuvable");
            return mav;
        }
    }

    // Modifier une spécialité existante
    @PostMapping("/edit/{id}")
    public ModelAndView editSpecialite(@PathVariable Long id, @ModelAttribute SpecialitesDTO specialitesDTO) {
        try {
            specialitesDTO.setId(id);
            specialitesService.updateSpecialite(specialitesDTO);
            return new ModelAndView("redirect:/specialites/liste");
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("view", "specialites/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("specialite", specialitesDTO);
            mav.addObject("action", "edit");
            return mav;
        }
    }

    // Supprimer une spécialité
    @GetMapping("/delete/{id}")
    public ModelAndView deleteSpecialite(@PathVariable Long id) {
        try {
            specialitesService.deleteSpecialite(id);
            return new ModelAndView("redirect:/specialites/liste");
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }
}
