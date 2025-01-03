package com.gestion.atelier.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.atelier.DTO.MouvementsStockDTO;
import com.gestion.atelier.services.MouvementsStockService;

@Controller
@RequestMapping("/mouvements")
public class MouvementsStockController {

    @Autowired
    private MouvementsStockService mouvementsStockService;

    @GetMapping("/historique")
    public ModelAndView historique() {
        ModelAndView mav = new ModelAndView("accueil");
        try {
            List<MouvementsStockDTO> stocks = mouvementsStockService.getAllMouvementsStock();

            mav.addObject("view", "mouvements/historique.jsp");
            mav.addObject("stocks", stocks);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mav;
    }
    
}
