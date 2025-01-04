package com.gestion.atelier.controllers;

import jakarta.servlet.http.HttpSession;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.atelier.DTO.AdminsDTO;
import com.gestion.atelier.DTO.GenreDTO;
import com.gestion.atelier.services.AdminsService;
import com.gestion.atelier.services.GenreService;
import com.gestion.atelier.utils.PasswordUtil;

@Controller
@RequestMapping("/connection")
public class ConnectionController {

    @Autowired
    private AdminsService adminsService;

    @Autowired
    private GenreService genreService;

    @PostMapping("/verification")
    public ModelAndView verificationConnection(
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp,
            HttpSession session) {

        ModelAndView mav = new ModelAndView("index");

        try {
            AdminsDTO admin = adminsService.verification(email, mdp);
            if (admin != null) {
                session.setAttribute("admin", admin);
                mav.setViewName("accueil");
            }
            else {
                mav.addObject("error", "Email ou mot de passe incorrect.");
            }
        } 
        catch (Exception e) {
            mav.addObject("error", e.getMessage());
        }

        return mav;
    }

    @SuppressWarnings("deprecation")
    @GetMapping("/inscription")
    public ModelAndView inscription() {
        ModelAndView mav = new ModelAndView("index");
        AdminsDTO admin = new AdminsDTO();

        admin.setNom("admin");
        admin.setPrenom("admin");
        admin.setDateNaissance(new Date(2004, 07, 13));

        GenreDTO genreDto = genreService.getById(Long.valueOf("1"));
        admin.setGenre(genreDto);
        admin.setEmail("admin@gmail.com");
        admin.setMotDePasse(PasswordUtil.hashPassword("admin"));

        adminsService.createAdmin(admin);

        return mav;
    }

    // Méthode pour gérer la déconnexion
    @GetMapping("/deconnexion")
    public ModelAndView deconnexion(HttpSession session) {
        ModelAndView mav = new ModelAndView("index");
        session.invalidate();

        return mav;
    }
}
