package com.gestion.atelier.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/connection")
public class ConnectionController {
    @PostMapping("/verification")
    public ModelAndView verificationConnection(@RequestParam("email") String email, @RequestParam("mdp") String mdp) {
        ModelAndView mav = new ModelAndView("accueil");

        return mav;
    }
}
