package com.gestion.atelier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.atelier.DTO.MarquesDTO;
import com.gestion.atelier.services.MarquesService;

@Controller
@RequestMapping("/marques")
public class MarquesController {

    @Autowired
    private MarquesService marquesService;

    // Afficher la liste des marques
    @GetMapping("/liste")
    public ModelAndView listMarques() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("view", "marques/liste.jsp");
        mav.addObject("marques", marquesService.getAll());
        return mav;
    }

    // Afficher le formulaire de création
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("view", "marques/form.jsp");
        mav.addObject("action", "create");
        return mav;
    }

    // Créer une nouvelle marque
    @PostMapping("/create")
    public ModelAndView createMarque(@ModelAttribute("marque") MarquesDTO marquesDTO) {
        try {
            marquesService.createMarque(marquesDTO);
            return new ModelAndView("redirect:/marques/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("view", "marques/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("marque", marquesDTO);
            mav.addObject("action", "create");
            return mav;
        }
    }

    // Afficher le formulaire de modification
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("index");
        try {
            MarquesDTO marque = marquesService.getById(id);
            mav.addObject("view", "marques/form.jsp");
            mav.addObject("marque", marque);
            mav.addObject("action", "edit");
        } 
        catch (Exception e) {
            mav.setViewName("index");
            mav.addObject("view", "error");
            mav.addObject("message", "Marque introuvable");
        }
        return mav;
    }

    // Mettre à jour une marque
    @PostMapping("/edit/{id}")
    public ModelAndView updateMarque(@PathVariable Long id, @ModelAttribute("marque") MarquesDTO marquesDTO) {
        try {
            marquesService.updateMarque(id, marquesDTO);
            return new ModelAndView("redirect:/marques/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("view", "marques/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("marque", marquesDTO);
            mav.addObject("action", "edit");
            return mav;
        }
    }

    // Supprimer une marque
    @GetMapping("/delete/{id}")
    public ModelAndView deleteMarque(@PathVariable Long id) {
        try {
            marquesService.deleteMarque(id);
            return new ModelAndView("redirect:/marques/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("view", "error");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }
}
