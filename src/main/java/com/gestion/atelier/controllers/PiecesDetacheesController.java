package com.gestion.atelier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.services.PiecesDetacheesService;

@Controller
@RequestMapping("/pieces")
public class PiecesDetacheesController {

    @Autowired
    private PiecesDetacheesService piecesDetacheesService;

    // Afficher la liste des pieces
    @GetMapping("/liste")
    public ModelAndView listPieces() {
        ModelAndView mav = new ModelAndView("accueil");
        mav.addObject("view", "pieces/liste.jsp");
        mav.addObject("pieces", piecesDetacheesService.getAll());
        return mav;
    }

    // Afficher le formulaire de création
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView mav = new ModelAndView("accueil");
        mav.addObject("view", "pieces/form.jsp");
        mav.addObject("action", "create");
        return mav;
    }

    // Créer une nouvelle piece
    @PostMapping("/create")
    public ModelAndView createPiece(@ModelAttribute("piece") PiecesDetacheesDTO piecesDTO) {
        try {
            piecesDetacheesService.createPiece(piecesDTO);
            return new ModelAndView("redirect:/pieces/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "pieces/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("piece", piecesDTO);
            mav.addObject("action", "create");
            return mav;
        }
    }

    // Afficher le formulaire de modification
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("accueil");
        try {
            PiecesDetacheesDTO piece = piecesDetacheesService.getById(id);
            mav.addObject("view", "pieces/form.jsp");
            mav.addObject("piece", piece);
            mav.addObject("action", "edit");
        } 
        catch (Exception e) {
            mav.setViewName("accueil");
            mav.addObject("view", "error");
            mav.addObject("message", "piece introuvable");
        }
        return mav;
    }

    // Mettre à jour une piece
    @PostMapping("/edit/{id}")
    public ModelAndView updatePiece(@PathVariable Long id, @ModelAttribute("piece") PiecesDetacheesDTO piecesDTO) {
        try {
            piecesDetacheesService.updatePiece(id, piecesDTO);
            return new ModelAndView("redirect:/pieces/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "pieces/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("piece", piecesDTO);
            mav.addObject("action", "edit");
            return mav;
        }
    }

    // Supprimer une piece
    @GetMapping("/delete/{id}")
    public ModelAndView deletePiece(@PathVariable Long id) {
        try {
            piecesDetacheesService.deletePiece(id);
            return new ModelAndView("redirect:/pieces/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "error");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }
}
