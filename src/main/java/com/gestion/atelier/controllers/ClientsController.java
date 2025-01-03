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

import com.gestion.atelier.DTO.ClientsDTO;
import com.gestion.atelier.DTO.GenreDTO;
import com.gestion.atelier.services.ClientsService;
import com.gestion.atelier.services.GenreService;

@Controller
@RequestMapping("/clients")
public class ClientsController {
    @Autowired
    private ClientsService clientsService;

    @Autowired
    private GenreService genreService;

    // Afficher la liste des clients
    @GetMapping("/liste")
    public ModelAndView getAllClients() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<ClientsDTO> clients = clientsService.getAll();

        modelAndView.addObject("view", "clients/liste.jsp");
        modelAndView.addObject("clients", clients);
        return modelAndView;
    }

    // Afficher le formulaire de création d'un client
    @GetMapping("/create")
    public ModelAndView createClientForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<GenreDTO> genres = genreService.getAll();

        modelAndView.addObject("view", "clients/form.jsp");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("genres", genres);
        return modelAndView;
    }

    // Créer un nouveau client
    @PostMapping("/create")
    public ModelAndView createClient(@RequestParam("nom") String nom,
                                    @RequestParam("prenom") String prenom,
                                    @RequestParam("dateNaissance") String dateNaissance,
                                    @RequestParam("email") String email,
                                    @RequestParam("telephone") String telephone,
                                    @RequestParam("adresse") String adresse,
                                    @RequestParam("genre") String genreId) {
        ClientsDTO clientsDTO = new ClientsDTO();
        try {
            if (genreId != null && !genreId.isEmpty() && nom != null && prenom != null && dateNaissance != null && email != null && telephone != null && adresse != null) {
                Long id = Long.parseLong(genreId);
                GenreDTO selectedGenre = genreService.getById(id);

                Date date = Date.valueOf(dateNaissance);

                clientsDTO.setNom(nom);
                clientsDTO.setPrenom(prenom);
                clientsDTO.setDateNaissance(date);
                clientsDTO.setEmail(email);
                clientsDTO.setTelephone(telephone);
                clientsDTO.setAdresse(adresse);
                clientsDTO.setGenre(selectedGenre);
            }

            clientsService.createClient(clientsDTO);
            return new ModelAndView("redirect:/clients/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "clients/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("client", clientsDTO);
            mav.addObject("action", "create");
            return mav;
        }
    }


    // Afficher le formulaire de modification d'un client
    @GetMapping("/edit/{id}")
    public ModelAndView editClientForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            ClientsDTO client = clientsService.getById(id);
            List<GenreDTO> genre = genreService.getAll();

            modelAndView.addObject("view", "clients/form.jsp");
            modelAndView.addObject("genres", genre);
            modelAndView.addObject("client", client);
            return modelAndView;
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView();

            mav.setViewName("redirect:/clients/liste"); 
            mav.addObject("view", "error");
            mav.addObject("message", "Genre introuvable");        
            return mav;
        }
    }

    // Modifier un client existant
    @PostMapping("/edit/{id}")
    public ModelAndView editClient(@PathVariable Long id, 
                                @RequestParam("nom") String nom,
                                @RequestParam("prenom") String prenom,
                                @RequestParam("dateNaissance") String dateNaissance,
                                @RequestParam("email") String email,
                                @RequestParam("telephone") String telephone,
                                @RequestParam("adresse") String adresse,
                                @RequestParam("genre") String genreId) throws Exception {
        ClientsDTO clientExist = new ClientsDTO();
        if (id != null) {
            clientExist = clientsService.getById(id);
        }
                                    
        try {
            if (genreId != null && nom != null && prenom != null && dateNaissance != null && email != null && telephone != null && adresse != null) {
                Long idGenre = Long.parseLong(genreId);
                GenreDTO selectedGenre = genreService.getById(idGenre);

                Date date = Date.valueOf(dateNaissance);

                clientExist.setNom(nom);
                clientExist.setPrenom(prenom);
                clientExist.setDateNaissance(date);
                clientExist.setEmail(email);
                clientExist.setTelephone(telephone);
                clientExist.setAdresse(adresse);
                clientExist.setGenre(selectedGenre);
            }

            clientsService.updateClient(id, clientExist);
            return new ModelAndView("redirect:/clients/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "clients/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("client", clientExist);
            mav.addObject("action", "edit");
            return mav;
        }
    }

    // Supprimer un client
    @GetMapping("/delete/{id}")
    public ModelAndView deleteClient(@PathVariable Long id) {
        try {
            clientsService.deleteClient(id);
            return new ModelAndView("redirect:/clients/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }
    
}
