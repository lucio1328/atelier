package com.gestion.atelier.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.ComposantRecommandeDTO;
import com.gestion.atelier.mappers.ComposantRecommandeMapper;
import com.gestion.atelier.models.ComposantRecommande;
import com.gestion.atelier.repository.ComposantRecommandeRepository;

@Service
public class ComposantRecommandeService {
    @Autowired
    private ComposantRecommandeRepository composantRecommandeRepository;

    private final ComposantRecommandeMapper composantRecommandeMapper = ComposantRecommandeMapper.INSTANCE;

    public ComposantRecommandeDTO getById(Long id) {
        ComposantRecommande composantRecommande = composantRecommandeRepository.getById(id);
        return composantRecommandeMapper.composantRecommandeToComposantRecommandeDTO(composantRecommande);
    }

    // 
    public List<ComposantRecommandeDTO> getAll() {
        List<ComposantRecommande> composantRecommandeList = composantRecommandeRepository.getAll();
        return composantRecommandeList.stream()
                              .map(composantRecommandeMapper::composantRecommandeToComposantRecommandeDTO)
                              .collect(Collectors.toList());
    }

    public ComposantRecommandeDTO createComposantRecommande(ComposantRecommandeDTO composantRecommandeDTO) {
        ComposantRecommande composantRecommande = composantRecommandeMapper.composantRecommandeDTOToComposantRecommande(composantRecommandeDTO);
        ComposantRecommande savedComposantRecommande = composantRecommandeRepository.save(composantRecommande);
        return composantRecommandeMapper.composantRecommandeToComposantRecommandeDTO(savedComposantRecommande);
    }

    public List<ComposantRecommandeDTO> getComposantRecommandeByDate() {
        List<ComposantRecommande> composantRecommandeList = composantRecommandeRepository.getComposantRecommandeByDate();
        return composantRecommandeList.stream()
                              .map(composantRecommandeMapper::composantRecommandeToComposantRecommandeDTO)
                              .collect(Collectors.toList());
    }

    public List<ComposantRecommandeDTO> getComposantRecommandeByDate(String mois, String annee) {
        List<ComposantRecommande> composantRecommandeList = composantRecommandeRepository.getComposantRecommandeByDate(mois,annee);
        return composantRecommandeList.stream()
                              .map(composantRecommandeMapper::composantRecommandeToComposantRecommandeDTO)
                              .collect(Collectors.toList());
    }

}
