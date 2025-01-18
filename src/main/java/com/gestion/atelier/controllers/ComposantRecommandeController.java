package com.gestion.atelier.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.atelier.DTO.AchatPiecesDTO;
import com.gestion.atelier.DTO.ComposantRecommandeDTO;
import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.services.ComposantRecommandeService;
import com.gestion.atelier.services.PiecesDetacheesService;

@Controller
@RequestMapping("/recommandes")
public class ComposantRecommandeController {

    @Autowired
    private PiecesDetacheesService piecesDetacheesService;

    @Autowired
    private ComposantRecommandeService composantRecommandeService;

    // Afficher le formulaire de création d'un comp rec
    @GetMapping("/create")
    public ModelAndView createComposantRecommandeForm() {
        ModelAndView modelAndView = new ModelAndView("accueil");
        List<PiecesDetacheesDTO> pieces = piecesDetacheesService.getAll();

        modelAndView.addObject("view", "recommande/form.jsp");
        modelAndView.addObject("pieces", pieces);
        return modelAndView;
    }

    // Créer un nouvel  composant rec
    @PostMapping("/create")
    public ModelAndView createComposantRecommande(
                                                @RequestParam("date") String date,
                                                @RequestParam("pieceDetachee") String pieceDetachee) {
        ComposantRecommandeDTO composantRecommandeDTO = new ComposantRecommandeDTO();
        try {
            if (date != null && pieceDetachee != null) {
                Date dateCR = Date.valueOf(date);
                Long idPiece = Long.parseLong(pieceDetachee);
                PiecesDetacheesDTO piecesDetacheesDTO = piecesDetacheesService.getById(idPiece);

                composantRecommandeDTO.setDate(dateCR);
                composantRecommandeDTO.setPieceDetachee(piecesDetacheesDTO);
            }

            composantRecommandeService.createComposantRecommande(composantRecommandeDTO);
            return new ModelAndView("redirect:/recommandes/liste");
        } 
        catch (Exception e) {
            ModelAndView mav = new ModelAndView("accueil");
            mav.addObject("view", "recommande/form.jsp");
            mav.addObject("error", "Erreur lors de la création : " + e.getMessage());
            mav.addObject("compRec", composantRecommandeDTO);
            return mav;
        }
    }

    @GetMapping("/liste")
    public ModelAndView listComposantsRecommandes() {
        ModelAndView mav = new ModelAndView("accueil");
        List<String> mois = List.of("janvier", "février", "mars", "avril", "mai", "juin",
                "juillet", "août", "septembre", "octobre", "novembre", "décembre");

        List<Integer> annee = new ArrayList<>();
        for(int i = 2010; i < 2026; i++) {
            annee.add(i);
        }
        mav.addObject("view", "recommande/liste.jsp");
        mav.addObject("composants", composantRecommandeService.getComposantRecommandeByDate());
        mav.addObject("mois", mois);
        mav.addObject("annees", annee);
        return mav;
    }

    @PostMapping("/recherche")
    public ModelAndView recherche(@RequestParam("mois") String unMois,
                                  @RequestParam("annee") String uneAnnee) {
        ModelAndView mav = new ModelAndView("accueil");

        List<String> mois = List.of("janvier", "février", "mars", "avril", "mai", "juin",
                "juillet", "août", "septembre", "octobre", "novembre", "décembre");

        List<Integer> annees = new ArrayList<>();
        for (int i = 2010; i <= 2025; i++) {
            annees.add(i);
        }

        mav.addObject("view", "recommande/liste.jsp");

        List<ComposantRecommandeDTO> composants;
        Map<String, List<ComposantRecommandeDTO>> groupedByMonth = new LinkedHashMap<>();

        for (String moisNom : mois) {
            groupedByMonth.put(moisNom, new ArrayList<>());
        }

        String moisParam = (unMois != null && !unMois.isEmpty()) ? unMois : null;
        String anneeParam = (uneAnnee != null && !uneAnnee.isEmpty()) ? uneAnnee : null;

        if (anneeParam != null && moisParam == null) {
            composants = composantRecommandeService.getComposantRecommandeByDate(moisParam, anneeParam);

            for (ComposantRecommandeDTO composant : composants) {
                String monthName = composant.getMonthName();
                groupedByMonth.computeIfAbsent(monthName, k -> new ArrayList<>()).add(composant);
            }
            mav.addObject("groupedByMonth", groupedByMonth);
        }
        else {
            composants = composantRecommandeService.getComposantRecommandeByDate(moisParam, anneeParam);
            mav.addObject("composants", composants);
        }

        mav.addObject("mois", mois);
        mav.addObject("annees", annees);

        mav.addObject("unMois", (moisParam != null) ? mois.get(Integer.parseInt(moisParam) - 1) : "");
        mav.addObject("uneAnnee", (anneeParam != null) ? uneAnnee : "");

        return mav;
    }
}
