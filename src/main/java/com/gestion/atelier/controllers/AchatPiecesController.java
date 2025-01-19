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

import com.gestion.atelier.DTO.AchatPiecesDTO;
import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.services.AchatPiecesService;
import com.gestion.atelier.services.PiecesDetacheesService;

@Controller
@RequestMapping("/achats")
public class AchatPiecesController {
    @Autowired
    private AchatPiecesService achatPiecesService;

    @Autowired
    private PiecesDetacheesService piecesDetacheesService;

    // Afficher la liste des achats
    @GetMapping("/liste")
    public ModelAndView getAllAchat() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<AchatPiecesDTO> achats = achatPiecesService.getAll();

        modelAndView.addObject("view", "achats/liste.jsp");
        modelAndView.addObject("achats", achats);
        return modelAndView;
    }

    // Afficher le formulaire de création d'un achat
    @GetMapping("/create")
    public ModelAndView createAchatForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<PiecesDetacheesDTO> pieces = piecesDetacheesService.getAll();

        modelAndView.addObject("view", "achats/form.jsp");
        modelAndView.addObject("action", "create");
        modelAndView.addObject("pieces", pieces);
        return modelAndView;
    }

    // Créer un nouvel achat
    @PostMapping("/create")
    public ModelAndView createAchat(@RequestParam("quantite") String quantite,
                                    @RequestParam("prixUnitaire") String prixUnitaire,
                                    @RequestParam("dateAchat") String dateAchat,
                                    @RequestParam("pieceDetachee") String pieceDetachee) {
        AchatPiecesDTO achatPiecesDTO = new AchatPiecesDTO();
        List<PiecesDetacheesDTO> pieces = piecesDetacheesService.getAll();
        try {
            if (quantite != null && prixUnitaire != null && dateAchat != null && pieceDetachee != null) {
                Integer quant = Integer.parseInt(quantite);
                Double prixU = Double.parseDouble(prixUnitaire);
                Date dateA = Date.valueOf(dateAchat);

                Long idPiece = Long.parseLong(pieceDetachee);
                PiecesDetacheesDTO piecesDetacheesDTO = piecesDetacheesService.getById(idPiece);

                achatPiecesDTO.setQuantite(quant);
                achatPiecesDTO.setQuantiteDisponible(quant);
                achatPiecesDTO.setPrixUnitaire(prixU);
                achatPiecesDTO.setDateAchat(dateA);
                achatPiecesDTO.setPieceDetachee(piecesDetacheesDTO);
            }

            achatPiecesService.createAchatPiece(achatPiecesDTO);
            return new ModelAndView("redirect:/achats/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "achats/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("achat", achatPiecesDTO);
            mav.addObject("action", "create");
            mav.addObject("pieces", pieces);
            return mav;
        }
    }

    // Afficher le formulaire de modification d'un achat
    @GetMapping("/edit/{id}")
    public ModelAndView editAchatForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("accueil");
        try {
            AchatPiecesDTO achatPiecesDTO = achatPiecesService.getById(id);
            List<PiecesDetacheesDTO> pieces = piecesDetacheesService.getAll();

            modelAndView.addObject("view", "achats/form.jsp");
            modelAndView.addObject("pieces", pieces);
            modelAndView.addObject("achat", achatPiecesDTO);
            return modelAndView;
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView();

            mav.setViewName("redirect:/achats/liste"); 
            mav.addObject("view", "error");       
            return mav;
        }
    }

    // Modifier un achat existant
    @PostMapping("/edit/{id}")
    public ModelAndView editAchat(@PathVariable Long id, 
                                @RequestParam("quantite") String quantite,
                                @RequestParam("quantiteDisponible") String quantiteDisponible,
                                @RequestParam("prixUnitaire") String prixUnitaire,
                                @RequestParam("dateAchat") String dateAchat,
                                @RequestParam("pieceDetachee") String pieceDetachee) {
                                    
        AchatPiecesDTO achatPiecesDTO = new AchatPiecesDTO();
        try {
            AchatPiecesDTO achatsExist = achatPiecesService.getById(id);

            if (quantite != null && prixUnitaire != null && dateAchat != null && pieceDetachee != null) {
                Integer quant = Integer.parseInt(quantite);
                Double prixU = Double.parseDouble(prixUnitaire);
                Date dateA = Date.valueOf(dateAchat);

                Long idPiece = Long.parseLong(pieceDetachee);
                PiecesDetacheesDTO piecesDetacheesDTO = piecesDetacheesService.getById(idPiece);

                achatsExist.setQuantite(quant);
                achatsExist.setPrixUnitaire(prixU);
                achatsExist.setDateAchat(dateA);
                achatsExist.setPieceDetachee(piecesDetacheesDTO);
            }
            if (quantiteDisponible != null) {
                Integer quantD = Integer.parseInt(quantiteDisponible);

                achatsExist.setQuantiteDisponible(quantD);
            }

            achatPiecesService.updateAchatPiece(id, achatsExist);
            return new ModelAndView("redirect:/achats/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "achats/form.jsp");
            mav.addObject("error", "Erreur lors de la mise à jour : " + e.getMessage());
            mav.addObject("achat", achatPiecesDTO);
            mav.addObject("action", "edit");
            return mav;
        }
    }

    // Supprimer un achat
    @GetMapping("/delete/{id}")
    public ModelAndView deleteAchat(@PathVariable Long id) {
        try {
            achatPiecesService.deleteAchatPiece(id);
            return new ModelAndView("redirect:/achats/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("message", "Erreur lors de la suppression : " + e.getMessage());
            return mav;
        }
    }
}
