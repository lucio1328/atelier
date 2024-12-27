package com.gestion.atelier.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.gestion.atelier.DTO.PiecesDetacheesDTO;
import com.gestion.atelier.services.PiecesDetacheesService;

@Controller
@RequestMapping("/api/pieces-detachees")
public class PiecesDetacheesController {

    @Autowired
    private PiecesDetacheesService piecesDetacheesService;

    @PostMapping("/test-create")
    public ResponseEntity<PiecesDetacheesDTO> testCreatePiece() {

        PiecesDetacheesDTO piecesDetacheesDTO = new PiecesDetacheesDTO();
        piecesDetacheesDTO.setNomPiece("Vis");
        piecesDetacheesDTO.setReference("V12345");
        piecesDetacheesDTO.setDescription("Vis standard pour montage");

        PiecesDetacheesDTO createdPiece = piecesDetacheesService.createPiece(piecesDetacheesDTO);

        return new ResponseEntity<>(createdPiece, HttpStatus.CREATED);
    }
}
