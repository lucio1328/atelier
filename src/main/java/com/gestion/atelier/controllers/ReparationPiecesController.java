package com.gestion.atelier.controllers;

import java.util.List;
import java.util.Map;

import com.gestion.atelier.models.AchatPieces;
import com.gestion.atelier.services.AchatPiecesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.models.PiecesDetachees;
import com.gestion.atelier.services.PiecesDetacheesService;
import com.gestion.atelier.services.ReparationPiecesService;
import com.gestion.atelier.services.ReparationsService;

@Controller
@RequestMapping("/reparationPieces")
public class ReparationPiecesController {
    @Autowired
    private ReparationPiecesService reparationPiecesService;
    @Autowired
    private ReparationsService reparationsService;
    @Autowired
    private PiecesDetacheesService piecesDetacheesService;

    // Afficher la liste des pieces par reparation
    @GetMapping("/liste/{reparationId}")
    public ModelAndView getPiecesByReparation(@PathVariable Long reparationId) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            ReparationsDTO reparation = reparationsService.getById(reparationId);
            Map<PiecesDetachees, Integer> pieces = reparationPiecesService.getPiecesByReparationId(reparationId);

            modelAndView.addObject("view", "reparationPieces/liste.jsp");
            modelAndView.addObject("reparation", reparation);
            modelAndView.addObject("pieces", pieces);
        } 
        catch (Exception e) {
            modelAndView.addObject("error", "Erreur lors de la récupération des pieces : " + e.getMessage());
        }
        return modelAndView;
    }

    // creation form
    @GetMapping("/create/{reparationId}")
    public ModelAndView createForm(@PathVariable Long reparationId) {
        ModelAndView mav = new ModelAndView("accueil");
        try {
            ReparationsDTO reparation = reparationsService.getById(reparationId);
            List<PiecesDetacheesDTO> pieces = piecesDetacheesService.getAll();

            mav.addObject("view", "reparationPieces/form.jsp");
            mav.addObject("reparation", reparation);
            mav.addObject("pieces", pieces);
            mav.addObject("action", "create");
            
        } 
        catch (Exception e) {
            mav.addObject("error", e.getMessage());
        }

        return mav;
    }

    // Ajouter une piece a une reparation
    @PostMapping("/create/{reparationId}")
    public ModelAndView addPiecesToReparation(@PathVariable Long reparationId, @RequestParam("piece") Long pieceId, @RequestParam("quantite") Integer quantite) {
        try {
            // verifierna oe ampy sa tsia zay vao save
            reparationPiecesService.save(reparationId, pieceId, quantite);
            return new ModelAndView("redirect:/reparationPieces/liste/" + reparationId);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("accueil");
            modelAndView.addObject("error", "Erreur lors de l'ajout des pieces : " + e.getMessage());
            return modelAndView;
        }
    }
}
