package com.gestion.atelier.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");

        return mav;
    }

    @GetMapping("/accueil")
    public ModelAndView accueil() {
        ModelAndView mav = new ModelAndView("accueil");

        return mav;
    }
}
