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

import com.gestion.atelier.DTO.ClientsDTO;
import com.gestion.atelier.DTO.ModelesDTO;
import com.gestion.atelier.DTO.OrdinateursDTO;
import com.gestion.atelier.services.ClientsService;
import com.gestion.atelier.services.ModelesService;
import com.gestion.atelier.services.OrdinateursService;

@Controller
@RequestMapping("/ordinateurs")
public class OrdinateursController {
    @Autowired
    private OrdinateursService ordinateursService;

    @Autowired
    private ModelesService modelesService;

    @Autowired
    private ClientsService clientsService;

    // Afficher la liste des ordinateurs
    @GetMapping("/liste")
    public ModelAndView getAllOrdinateurs() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<OrdinateursDTO> ordinateurs = ordinateursService.getAll();

        modelAndView.addObject("view", "ordinateurs/liste.jsp");
        modelAndView.addObject("ordinateurs", ordinateurs);
        return modelAndView;
    }

    // Afficher le formulaire de création d'un ordinateur
    @GetMapping("/create")
    public ModelAndView createOrdinateurForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<ModelesDTO> modeles = modelesService.getAll();
        List<ClientsDTO> clients = clientsService.getAll();

        modelAndView.addObject("view", "ordinateurs/form.jsp");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("modeles", modeles);
        modelAndView.addObject("clients", clients);
        return modelAndView;
    }

    // Créer un nouveau modèle
    @PostMapping("/create")
    public ModelAndView createOrdinateur(@RequestParam("numeroSerie") String numeroSerie, 
                                        @RequestParam("description") String description,
                                        @RequestParam("client") String client,
                                        @RequestParam("modele") String modele) {
        OrdinateursDTO ordinateursDTO = new OrdinateursDTO();
        try {
            if (description != null && numeroSerie != null && client != null && modele != null) {
                Long idClient = Long.parseLong(client);
                ClientsDTO selectedClient = clientsService.getById(idClient);

                Long idModele = Long.parseLong(modele);
                ModelesDTO selectedModele = modelesService.getById(idModele);

                ordinateursDTO.setNumeroSerie(numeroSerie);
                ordinateursDTO.setDescription(description);
                ordinateursDTO.setClient(selectedClient);
                ordinateursDTO.setModele(selectedModele);
            }

            ordinateursService.createOrdinateur(ordinateursDTO);
            return new ModelAndView("redirect:/ordinateurs/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "ordinateurs/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("ordinateur", ordinateursDTO);
            mav.addObject("action", "create");
            return mav;
        }
    }

    // Afficher le formulaire de modification d'un ordinateur
    @GetMapping("/edit/{id}")
    public ModelAndView editOrdinateurForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            OrdinateursDTO ordinateursDTO = ordinateursService.getById(id);
            
            List<ModelesDTO> modeles = modelesService.getAll();
            List<ClientsDTO> clients = clientsService.getAll();
    
            modelAndView.addObject("view", "ordinateurs/form.jsp");
            modelAndView.addObject("ordinateur", ordinateursDTO);
            modelAndView.addObject("modeles", modeles);
            modelAndView.addObject("clients", clients);

            return modelAndView;
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView();

            mav.setViewName("redirect:/ordinateurs/liste"); 
            mav.addObject("view", "error");       
            return mav;
        }
    }

    // Modifier un ordinateur existant
    @PostMapping("/edit/{id}")
    public ModelAndView editOrdinateur(@PathVariable Long id,
                                    @RequestParam("numeroSerie") String numeroSerie,
                                    @RequestParam("description") String description,
                                    @RequestParam("client") String client,
                                    @RequestParam("modele") String modele) throws Exception {
                                    
        // OrdinateursDTO ordinateursDTO = new OrdinateursDTO();
        try {
            OrdinateursDTO ordinateurExist = ordinateursService.getById(id);
            if (description != null && numeroSerie != null && client != null && modele != null) {
                Long idClient = Long.parseLong(client);
                ClientsDTO selectedClient = clientsService.getById(idClient);

                Long idModele = Long.parseLong(modele);
                ModelesDTO selectedModele = modelesService.getById(idModele);

                ordinateurExist.setNumeroSerie(numeroSerie);
                ordinateurExist.setDescription(description);
                ordinateurExist.setClient(selectedClient);
                ordinateurExist.setModele(selectedModele);
            }

            ordinateursService.updateOrdinateur(id, ordinateurExist);

            return new ModelAndView("redirect:/ordinateurs/liste");
        } 
        catch (Exception e) {
            throw new Exception(e.getMessage());
            // ModelAndView mav = new ModelAndView("accueil");
            
            // mav.addObject("view", "ordinateurs/form.jsp");
            // mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            // mav.addObject("ordinateur", ordinateursDTO);
            // mav.addObject("action", "edit");
            // return mav;
        }
    }

    // Supprimer un ordinateur
    @GetMapping("/delete/{id}")
    public ModelAndView deleteOrdinateur(@PathVariable Long id) {
        try {
            ordinateursService.deleteOrdinateur(id);
            return new ModelAndView("redirect:/ordinateurs/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }
}
