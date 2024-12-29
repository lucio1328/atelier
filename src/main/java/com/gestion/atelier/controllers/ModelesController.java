package com.gestion.atelier.controllers;

import com.gestion.atelier.DTO.ModelesDTO;
import com.gestion.atelier.DTO.MarquesDTO;
import com.gestion.atelier.services.ModelesService;
import com.gestion.atelier.services.MarquesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/modeles")
public class ModelesController {

    @Autowired
    private ModelesService modelesService;

    @Autowired
    private MarquesService marquesService;

    // Afficher la liste des modèles
    @GetMapping("/liste")
    public ModelAndView getAllModeles() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<ModelesDTO> modeles = modelesService.getAll();

        modelAndView.addObject("view", "modeles/liste.jsp");
        modelAndView.addObject("modeles", modeles);
        return modelAndView;
    }

    // Afficher le formulaire de création d'un modèle
    @GetMapping("/create")
    public ModelAndView createModeleForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<MarquesDTO> marques = marquesService.getAll();

        modelAndView.addObject("view", "modeles/form.jsp");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("marques", marques);
        return modelAndView;
    }

    // Créer un nouveau modèle
    @PostMapping("/create")
    public ModelAndView createModele(@RequestParam("nomModele") String nomModele, @RequestParam("marque") String marqueId) {
        ModelesDTO modelesDTO = new ModelesDTO();
        try {
            if (marqueId != null && !marqueId.isEmpty() && nomModele != null) {
                Long id = Long.parseLong(marqueId);
                MarquesDTO selectedMarque = marquesService.getById(id);

                modelesDTO.setNomModele(nomModele);
                modelesDTO.setMarque(selectedMarque);
            }

            modelesService.createModele(modelesDTO);
            return new ModelAndView("redirect:/modeles/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "modeles/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("modele", modelesDTO);
            mav.addObject("action", "create");
            return mav;
        }
    }


    // Afficher le formulaire de modification d'un modèle
    @GetMapping("/edit/{id}")
    public ModelAndView editModeleForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            ModelesDTO modele = modelesService.getById(id);
            List<MarquesDTO> marques = marquesService.getAll();

            modelAndView.addObject("view", "modeles/form.jsp");
            modelAndView.addObject("marques", marques);
            modelAndView.addObject("modele", modele);
            return modelAndView;
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView();

            mav.setViewName("redirect:/modeles/liste"); 
            mav.addObject("view", "error");
            mav.addObject("message", "Marque introuvable");        
            return mav;
        }
    }

    // Modifier un modèle existant
    @PostMapping("/edit/{id}")
    public ModelAndView editModele(@PathVariable Long id, 
                                @RequestParam("nomModele") String nomModele, 
                                @RequestParam("marque") String marqueId) {
                                    
        ModelesDTO modelesDTO = new ModelesDTO();
        try {
            ModelesDTO modeleExist = modelesService.getById(id);

            if (nomModele != null && !nomModele.isEmpty()) {
                modeleExist.setNomModele(nomModele);
            }

            if (marqueId != null && !marqueId.isEmpty()) {
                Long idMarque = Long.parseLong(marqueId);
                MarquesDTO selectedMarque = marquesService.getById(idMarque);
                modeleExist.setMarque(selectedMarque);
            }

            modelesService.updateModele(id, modeleExist);
            return new ModelAndView("redirect:/modeles/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "modeles/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("modele", modelesDTO);
            mav.addObject("action", "edit");
            return mav;
        }
    }

    // Supprimer un modèle
    @GetMapping("/delete/{id}")
    public ModelAndView deleteModele(@PathVariable Long id) {
        try {
            modelesService.deleteModele(id);
            return new ModelAndView("redirect:/modeles/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }
}
