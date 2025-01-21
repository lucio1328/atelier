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
import com.gestion.atelier.DTO.TypeReparationDTO;
import com.gestion.atelier.services.TypeReparationService;

@Controller
@RequestMapping("/typeReparation")
public class TypeReparationController {
        @Autowired
        private TypeReparationService typeReparationService;


        @GetMapping("/liste")
        public ModelAndView getAllType() {
            ModelAndView modelAndView = new ModelAndView("accueil");
            List<TypeReparationDTO> types = typeReparationService.getAll();
    
            modelAndView.addObject("view", "typereparation/liste.jsp");
            modelAndView.addObject("typeReparation", types);
            return modelAndView;
        }

    // Afficher le formulaire de création d'un type de reparation

    @GetMapping("/create")
    public ModelAndView createTypeForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");

        modelAndView.addObject("view", "typereparation/form.jsp");
        modelAndView.addObject("action", "create");
        return modelAndView;
    }

    // Créer un nouveau type de reparation
    @PostMapping("/create")
    public ModelAndView createType(@RequestParam("libelle") String libelle,
                                    @RequestParam("commission") String commission) {
        TypeReparationDTO typeReparationDTO = new TypeReparationDTO();
        try {
            if (libelle != null && commission != null) {
                Double commi = Double.parseDouble(commission);
        
                typeReparationDTO.setLibelle(libelle);
                typeReparationDTO.setCommission(commi);
            }

            typeReparationService.createTypeReparation(typeReparationDTO);
            return new ModelAndView("redirect:/typeReparation/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "typereparation/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("typeReparation", typeReparationDTO);
            mav.addObject("action", "create");
            return mav;
        }
    }

    // Afficher le formulaire de modification d'un type
    @GetMapping("/edit/{id}")
    public ModelAndView editTypeForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            TypeReparationDTO type = typeReparationService.getById(id);

            modelAndView.addObject("view", "typereparation/form.jsp");
            modelAndView.addObject("typeReparation", type);
            return modelAndView;
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView();

            mav.setViewName("redirect:/typeReparation/liste"); 
            mav.addObject("view", "error");
            mav.addObject("message", "Type introuvable");        
            return mav;
        }
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editTypr(@PathVariable Long id, 
                                @RequestParam("libelle") String libelle,
                                @RequestParam("commission") String commission) throws Exception {
        TypeReparationDTO typeExist = new TypeReparationDTO();
        if (id != null) {
            typeExist = typeReparationService.getById(id);
        }
                                    
        try {
            if (libelle != null && commission != null) {
               Double commi = Double.parseDouble(commission);

               typeExist.setLibelle(libelle);
               typeExist.setCommission(commi);
            }

            typeReparationService.updateTypeReparation(typeExist);
            return new ModelAndView("redirect:/typeReparation/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "typereparation/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("technicien", typeExist);
            mav.addObject("action", "edit");
            return mav;
        }
    }
}
