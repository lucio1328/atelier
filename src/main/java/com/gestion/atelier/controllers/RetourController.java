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

import com.gestion.atelier.DTO.AchatPiecesDTO;
import com.gestion.atelier.DTO.CategoriesDTO;
import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.DTO.RetourDTO;
import com.gestion.atelier.DTO.TypeReparationDTO;
import com.gestion.atelier.services.CategoriesService;
import com.gestion.atelier.services.ReparationsService;
import com.gestion.atelier.services.RetourService;
import com.gestion.atelier.services.TypeReparationService;

@Controller
@RequestMapping("/retour")
public class RetourController {
    
    @Autowired
    private RetourService retourService;

    @Autowired
    private ReparationsService reparationsService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private TypeReparationService typeReparationService;

    @GetMapping("/create")
    public ModelAndView createRetourForm(@RequestParam("idReparation") String idReparation) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try{
            Long idRep = Long.parseLong(idReparation);
            modelAndView.addObject("view", "retour/form.jsp");
            modelAndView.addObject("action", "create");
            modelAndView.addObject("idRep", idRep);
        } catch (Exception e){

        }
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createRetour(@RequestParam("idReparation") String idReparation,
                                    @RequestParam("dateRetour") String dateRetour) {
        RetourDTO retourDTO = new RetourDTO();
        try {

            if (idReparation != null && dateRetour != null) {
                Date dateR = Date.valueOf(dateRetour);
                Long idRep = Long.parseLong(idReparation);

                ReparationsDTO reparationsDTO = reparationsService.getById(idRep);

                retourDTO.setDateRetour(dateR);
                retourDTO.setReparations(reparationsDTO);
            }
        
            retourService.createRetour(retourDTO);
            return new ModelAndView("redirect:/retour/liste");
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "retour/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("idRep", Long.parseLong(idReparation));
            mav.addObject("action", "create");
            return mav;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editRetourForm(@PathVariable("id") Long idRetour) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try{
            RetourDTO retourDTO = retourService.getById(idRetour);
            modelAndView.addObject("view", "retour/form.jsp");
            modelAndView.addObject("retour", retourDTO);
        } catch (Exception e){

        }
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateRetour(@PathVariable("id") Long idRetour,
                                    @RequestParam("idReparation") String idReparation,
                                    @RequestParam("dateRetour") String dateRetour) {
        RetourDTO retourDTO = new RetourDTO();
        try {

            if (idReparation != null && dateRetour != null) {
                Date dateR = Date.valueOf(dateRetour);
                Long idRep = Long.parseLong(idReparation);

                ReparationsDTO reparationsDTO = reparationsService.getById(idRep);

                retourDTO.setDateRetour(dateR);
                retourDTO.setReparations(reparationsDTO);
            }
        
            retourService.updateRetour(idRetour, retourDTO);
            return new ModelAndView("redirect:/retour/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "retour/form.jsp");
            mav.addObject("error", "Erreur lors de la modification : " + e.getMessage());
            mav.addObject("retour", retourDTO);
            return mav;
        }
    }

    // Supprimer retour
    @GetMapping("/delete/{id}")
    public ModelAndView deleteRetour(@PathVariable Long id) {
        try {
            retourService.deleteRetour(id);
            return new ModelAndView("redirect:/retour/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "error");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }

    // Afficher la liste des retours
    @GetMapping("/liste")
    public ModelAndView getAllRetour() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<RetourDTO> retours = retourService.getAll();
        List<CategoriesDTO> categories = categoriesService.getAllCategories();
        List<TypeReparationDTO> typeReparations = typeReparationService.getAll();

        modelAndView.addObject("view", "retour/liste.jsp");
        modelAndView.addObject("retours", retours);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("typeReparations", typeReparations);
        return modelAndView;
    }

    @PostMapping("/recherche")
    public ModelAndView recherche(@RequestParam("typeReparation") String typeRep,
                                    @RequestParam("categorie") String categorie) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<CategoriesDTO> categories = categoriesService.getAllCategories();
        List<TypeReparationDTO> typeReparations = typeReparationService.getAll();
        List<RetourDTO> retoursRecherche = retourService.getByCategorieTypeReparation(Long.parseLong(categorie),Long.parseLong(typeRep));

        modelAndView.addObject("view", "retour/liste.jsp");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("typeReparations", typeReparations);
        modelAndView.addObject("resultatsRecherche", retoursRecherche);

        return modelAndView;
    }
}
