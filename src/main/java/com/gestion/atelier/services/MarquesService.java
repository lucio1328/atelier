package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.MarquesDTO;
import com.gestion.atelier.mappers.MarquesMapper;
import com.gestion.atelier.repository.MarquesRepository;
import com.gestion.atelier.models.Marques;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarquesService {

    @Autowired
    private MarquesRepository marquesRepository;

    private final MarquesMapper marquesMapper = MarquesMapper.INSTANCE;

    //
    public MarquesDTO getById(Long id) throws Exception {
        Marques marque = marquesRepository.findById(id).orElse(null);
        if (marque == null) {
            throw new Exception("Marque introuvable");
        }
        return marquesMapper.marquesToMarquesDTO(marque);
    }

    //
    public List<MarquesDTO> getAll() {
        List<Marques> marques = marquesRepository.findAll();
        return marques.stream()
                      .map(marquesMapper::marquesToMarquesDTO)
                      .collect(Collectors.toList());
    }

    //
    public MarquesDTO createMarque(MarquesDTO marquesDTO) {
        Marques marque = marquesMapper.marquesDTOToMarques(marquesDTO);
        Marques savedMarque = marquesRepository.save(marque);
        return marquesMapper.marquesToMarquesDTO(savedMarque);
    }

    //
    public MarquesDTO updateMarque(Long id, MarquesDTO marquesDTO) throws Exception {
        Marques existingMarque = marquesRepository.findById(id).orElse(null);
        if (existingMarque == null) {
            throw new Exception("Marque introuvable pour la mise à jour");
        }
        Marques marqueToUpdate = marquesMapper.marquesDTOToMarques(marquesDTO);
        Marques updatedMarque = marquesRepository.save(marqueToUpdate);

        return marquesMapper.marquesToMarquesDTO(updatedMarque);
    }

    //
    public void deleteMarque(Long id) throws Exception {
        Marques marque = marquesRepository.findById(id).orElse(null);
        if (marque == null) {
            throw new Exception("Marque introuvable pour suppression");
        }
        marquesRepository.deleteById(id);
    }
}
