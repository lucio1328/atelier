package com.gestion.atelier.controllers;

import java.sql.Date;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.atelier.DTO.ClientsDTO;
import com.gestion.atelier.DTO.OrdinateursDTO;
import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.DTO.StatutDTO;
import com.gestion.atelier.DTO.TechniciensDTO;
import com.gestion.atelier.DTO.TypeReparationDTO;
import com.gestion.atelier.services.ClientsService;
import com.gestion.atelier.services.OrdinateursService;
import com.gestion.atelier.services.ReparationsService;
import com.gestion.atelier.services.StatutService;
import com.gestion.atelier.services.TechniciensService;
import com.gestion.atelier.services.TypeReparationService;

@Controller
@RequestMapping("/reparations")
public class ReparationsController {
    @Autowired
    private ReparationsService reparationsService;

    @Autowired
    private TechniciensService techniciensService;

    @Autowired
    private ClientsService clientsService;

    @Autowired 
    private OrdinateursService ordinateursService;

    @Autowired
    private StatutService statutService;

    @Autowired
    private TypeReparationService typeReparationService;

    @PostMapping("/rechercheDate")
    public ModelAndView rechercheDate(@RequestParam("dateMin") String dateMin, @RequestParam("dateMax") String dateMax){
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<ReparationsDTO> reparations = reparationsService.getBetweenDate(Date.valueOf(dateMin), Date.valueOf(dateMax));

        List<ClientsDTO> clients = new ArrayList<>();
        for (ReparationsDTO reparation : reparations){
            clients.add(reparation.getClient());
        }

        modelAndView.addObject("view", "reparations/listeClient.jsp");
        modelAndView.addObject("reparations", reparations);
        modelAndView.addObject("clients", clients);

        return modelAndView;

    }

    // Afficher la liste des reparations
    @GetMapping("/liste")
    public ModelAndView getAllReparations() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<ReparationsDTO> reparations = reparationsService.getAll();
        List<TypeReparationDTO> typeReparation = typeReparationService.getAll();

        modelAndView.addObject("view", "reparations/liste.jsp");
        modelAndView.addObject("reparations", reparations);
        modelAndView.addObject("typeReparation", typeReparation);
        modelAndView.addObject("type", "Tous");

        return modelAndView;
    }

    @PostMapping("/recherche")
    public ModelAndView recherche(@RequestParam("typeReparation") String typeReparation){
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {

            if (typeReparation != null && typeReparation.equalsIgnoreCase("tous")) {
                return new ModelAndView("redirect:/reparations/liste");
            }

            List<ReparationsDTO> reparations = reparationsService.getByType(typeReparation);
            List<TypeReparationDTO> typeReparations = typeReparationService.getAll();

            modelAndView.addObject("typeReparation", typeReparations);
            modelAndView.addObject("type", typeReparationService.getById(Long.parseLong(typeReparation)).getLibelle());
            modelAndView.addObject("view", "reparations/liste.jsp");
            modelAndView.addObject("reparations", reparations);
            return modelAndView;
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return modelAndView;

    }

    // Afficher le formulaire de création d'une reparation
    @GetMapping("/create")
    public ModelAndView createReparationForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");

        List<TechniciensDTO> techniciens = techniciensService.getAll();
        List<StatutDTO> statuts = statutService.getAll();
        List<OrdinateursDTO> ordinateurs = ordinateursService.getAll();
        List<TypeReparationDTO> typeReparation = typeReparationService.getAll();

        modelAndView.addObject("view", "reparations/form.jsp");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("techniciens", techniciens);
        modelAndView.addObject("statuts", statuts);
        modelAndView.addObject("ordinateurs", ordinateurs);
        modelAndView.addObject("typeReparation", typeReparation);

        return modelAndView;
    }

    // Créer un nouveau technicien
    @PostMapping("/create")
    public ModelAndView createReparation(@RequestParam("description") String description,
                                    @RequestParam("dateDebut") String dateDebut,
                                    @RequestParam("technicien") String technicien,
                                    @RequestParam("ordinateur") String ordinateur,
                                    @RequestParam("statut") String statut,
                                    @RequestParam("typeReparation") String typeReparation) {
        ReparationsDTO reparationsDTO = new ReparationsDTO();

        List<TechniciensDTO> techniciens = techniciensService.getAll();
        List<StatutDTO> statuts = statutService.getAll();
        List<OrdinateursDTO> ordinateurs = ordinateursService.getAll();
        List<TypeReparationDTO> typeReparations = typeReparationService.getAll();
        try {
            if (description != null && dateDebut != null && technicien != null && ordinateur != null && statut != null && typeReparation != null) {
                Long idTech = Long.parseLong(technicien);
                TechniciensDTO technicienDTO = techniciensService.getById(idTech);

                Long idOrdinateur = Long.parseLong(ordinateur);
                OrdinateursDTO ordinateurDTO = ordinateursService.getById(idOrdinateur);
                
                ClientsDTO clientDTO = clientsService.getById(ordinateurDTO.getClient().getId());

                Long idStatut = Long.parseLong(statut);
                StatutDTO statuDTO = statutService.getById(idStatut);

                Long idTypeReparation = Long.parseLong(typeReparation);
                TypeReparationDTO typeReparationDTO = typeReparationService.getById(idTypeReparation);

                Date dateD = Date.valueOf(dateDebut);

                reparationsDTO.setDescription(description);
                reparationsDTO.setDateDebut(dateD);
                reparationsDTO.setTechnicien(technicienDTO);
                reparationsDTO.setOrdinateur(ordinateurDTO);
                reparationsDTO.setStatut(statuDTO);
                reparationsDTO.setClient(clientDTO);
                reparationsDTO.setTypeReparation(typeReparationDTO);

            }
            reparationsService.createReparation(reparationsDTO);
            return new ModelAndView("redirect:/reparations/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "reparations/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("reparation", reparationsDTO);
            mav.addObject("techniciens", techniciens);
            mav.addObject("statuts", statuts);
            mav.addObject("ordinateurs", ordinateurs);
            mav.addObject("typeReparation", typeReparations);

            return mav;
        }
    }

    // Afficher le formulaire de modification d'une reparation
    @GetMapping("/edit/{id}")
    public ModelAndView editReparationForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            ReparationsDTO reparation = reparationsService.getById(id);

            List<TechniciensDTO> techniciens = techniciensService.getAll();
            List<StatutDTO> statuts = statutService.getAll();
            List<OrdinateursDTO> ordinateurs = ordinateursService.getAll();
            List<TypeReparationDTO> typeReparations = typeReparationService.getAll();

            modelAndView.addObject("view", "reparations/form.jsp");
            modelAndView.addObject("techniciens", techniciens);
            modelAndView.addObject("statuts", statuts);
            modelAndView.addObject("ordinateurs", ordinateurs);
            modelAndView.addObject("reparation", reparation);
            modelAndView.addObject("typeReparation", typeReparations);

            return modelAndView;
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView();

            mav.setViewName("redirect:/reparations/liste"); 
            mav.addObject("view", "error");       
            return mav;
        }
    }

    // Modifier une reparation existante
    @PostMapping("/edit/{id}")
    public ModelAndView editReparation(@PathVariable Long id, 
                                    @RequestParam("description") String description,
                                    @RequestParam("dateDebut") String dateDebut,
                                    @RequestParam("dateFin") String dateFin,
                                    @RequestParam("technicien") String technicien,
                                    @RequestParam("ordinateur") String ordinateur,
                                    @RequestParam("statut") String statut) throws Exception {
        ReparationsDTO reparationsDTO = new ReparationsDTO();

        List<TechniciensDTO> techniciens = techniciensService.getAll();
        List<StatutDTO> statuts = statutService.getAll();
        List<OrdinateursDTO> ordinateurs = ordinateursService.getAll();
        List<TypeReparationDTO> typeReparations = typeReparationService.getAll();
        if (id != null) {
            reparationsDTO = reparationsService.getById(id);
        }
                                    
        try {
            if (description != null && dateDebut != null && technicien != null && ordinateur != null && statut != null) {
                Long idTech = Long.parseLong(technicien);
                TechniciensDTO technicienDTO = techniciensService.getById(idTech);

                Long idOrdinateur = Long.parseLong(ordinateur);
                OrdinateursDTO ordinateurDTO = ordinateursService.getById(idOrdinateur);

                ClientsDTO clientDTO = clientsService.getById(ordinateurDTO.getClient().getId());

                Long idStatut = Long.parseLong(statut);
                StatutDTO statuDTO = statutService.getById(idStatut);

                Date dateD = Date.valueOf(dateDebut);
                Date dateF = Date.valueOf(dateFin);

                reparationsDTO.setDescription(description);
                reparationsDTO.setDateDebut(dateD);
                reparationsDTO.setDateFin(dateF);
                reparationsDTO.setTechnicien(technicienDTO);
                reparationsDTO.setOrdinateur(ordinateurDTO);
                reparationsDTO.setStatut(statuDTO);
                reparationsDTO.setClient(clientDTO);              
            }

            reparationsService.updateReparation(id, reparationsDTO);
            return new ModelAndView("redirect:/reparations/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "reparations/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("reparation", reparationsDTO);
            mav.addObject("techniciens", techniciens);
            mav.addObject("statuts", statuts);
            mav.addObject("ordinateurs", ordinateurs);
            mav.addObject("typeReparation", typeReparations);
            mav.addObject("action", "edit");
            return mav;
        }
    }

    // Supprimer ue reparation
    @GetMapping("/delete/{id}")
    public ModelAndView deleteReparation(@PathVariable Long id) {
        try {
            reparationsService.deleteReparation(id);
            return new ModelAndView("redirect:/reparations/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }

    @GetMapping("/rechercheClient")
    public ModelAndView rechercheClient(){
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<ReparationsDTO> reparations = reparationsService.getByDate();

        List<ClientsDTO> clients = new ArrayList<>();
        for (ReparationsDTO reparation : reparations){
            clients.add(reparation.getClient());
        }

        modelAndView.addObject("view", "reparations/listeClient.jsp");
        modelAndView.addObject("reparations", reparations);
        modelAndView.addObject("clients", clients);

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") Long idRep) throws Exception {
        ModelAndView mav = new ModelAndView("accueil");

        ReparationsDTO reparation = reparationsService.getById(idRep);

        mav.addObject("view", "reparations/details.jsp");
        mav.addObject("reparation", reparation);

        return mav;
    }

}
