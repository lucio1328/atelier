package com.gestion.atelier.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.atelier.DTO.SpecialitesDTO;
import com.gestion.atelier.DTO.TechniciensDTO;
import com.gestion.atelier.services.SpecialiteTechnicienService;
import com.gestion.atelier.services.SpecialitesService;
import com.gestion.atelier.services.TechniciensService;

@Controller
@RequestMapping("/specialiteTechnicien")
public class SpecialiteTechnicienController {

    @Autowired
    private SpecialiteTechnicienService specialiteTechnicienService;

    @Autowired
    private TechniciensService techniciensService;

    @Autowired
    private SpecialitesService specialitesService;

    // Afficher la liste des spécialités par technicien
    @GetMapping("/liste/{technicienId}")
    public ModelAndView getSpecialitesByTechnicien(@PathVariable Long technicienId) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            TechniciensDTO technicien = techniciensService.getById(technicienId);
            List<SpecialitesDTO> specialites = specialiteTechnicienService.getByTechnicienId(technicienId);

            modelAndView.addObject("view", "specialiteTechnicien/liste.jsp");
            modelAndView.addObject("technicien", technicien);
            modelAndView.addObject("specialites", specialites);
        } 
        catch (Exception e) {
            modelAndView.addObject("error", "Erreur lors de la récupération des spécialités : " + e.getMessage());
        }
        return modelAndView;
    }

    // Afficher le formulaire d'ajout d'une spécialité à un technicien
    @GetMapping("/create/{technicienId}")
    public ModelAndView createSpecialiteForm(@PathVariable Long technicienId) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            TechniciensDTO technicien = techniciensService.getById(technicienId);
            List<SpecialitesDTO> allSpecialites = specialitesService.getAllSpecialites();

            modelAndView.addObject("view", "specialiteTechnicien/form.jsp");
            modelAndView.addObject("technicien", technicien);
            modelAndView.addObject("specialites", allSpecialites);
            modelAndView.addObject("action", "create");
        } 
        catch (Exception e) {
            modelAndView.addObject("error", "Erreur lors de la récupération des données : " + e.getMessage());
        }
        return modelAndView;
    }

    // Ajouter une spécialité à un technicien
    @PostMapping("/create/{technicienId}")
    public ModelAndView addSpecialitesToTechnicien(@PathVariable Long technicienId, @RequestParam("specialiteIds") List<Long> specialiteIds) {
        try {
            for (Long specialiteId : specialiteIds) {
                specialiteTechnicienService.save(technicienId, specialiteId);
            }
            return new ModelAndView("redirect:/specialiteTechnicien/liste/" + technicienId);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("accueil");
            modelAndView.addObject("error", "Erreur lors de l'ajout des spécialités : " + e.getMessage());
            return modelAndView;
        }
    }

    // Supprimer une spécialité d'un technicien
    // @GetMapping("/delete/{technicienId}/{specialiteId}")
    // public ModelAndView deleteSpecialiteFromTechnicien(@PathVariable Long technicienId, @PathVariable Long specialiteId) {
    //     try {
    //         specialiteTechnicienService.delete(technicienId, specialiteId);
    //         return new ModelAndView("redirect:/specialiteTechnicien/liste/" + technicienId);
    //     } 
    //     catch (Exception e) {
    //         ModelAndView modelAndView = new ModelAndView("accueil");
    //         modelAndView.addObject("error", "Erreur lors de la suppression de la spécialité : " + e.getMessage());
    //         return modelAndView;
    //     }
    // }
}
