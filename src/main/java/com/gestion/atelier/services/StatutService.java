package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.StatutDTO;
import com.gestion.atelier.mappers.StatutMapper;
import com.gestion.atelier.repository.StatutRepository;
import com.gestion.atelier.models.Statut;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatutService {

    @Autowired
    private StatutRepository statutRepository;

    private final StatutMapper statutMapper = StatutMapper.INSTANCE;

    //
    public StatutDTO getById(Long id) throws Exception {
        Statut statut = statutRepository.findById(id).orElse(null);
        if (statut == null) {
            throw new Exception("Statut introuvable");
        }
        return statutMapper.statutToStatutDTO(statut);
    }

    //
    public List<StatutDTO> getAll() {
        List<Statut> statuts = statutRepository.findAll();
        return statuts.stream()
                      .map(statutMapper::statutToStatutDTO)
                      .collect(Collectors.toList());
    }

    //
    public StatutDTO createStatut(StatutDTO statutDTO) {
        Statut statut = statutMapper.statutDTOToStatut(statutDTO);
        Statut savedStatut = statutRepository.save(statut);
        return statutMapper.statutToStatutDTO(savedStatut);
    }

    //
    public StatutDTO updateStatut(Long id, StatutDTO statutDTO) throws Exception {
        Statut existingStatut = statutRepository.findById(id).orElse(null);
        if (existingStatut == null) {
            throw new Exception("Statut introuvable pour la mise à jour");
        }
        Statut statutToUpdate = statutMapper.statutDTOToStatut(statutDTO);

        Statut updatedStatut = statutRepository.save(statutToUpdate);

        return statutMapper.statutToStatutDTO(updatedStatut);
    }

    //
    public void deleteStatut(Long id) throws Exception {
        Statut statut = statutRepository.findById(id).orElse(null);
        if (statut == null) {
            throw new Exception("Statut introuvable pour suppression");
        }
        statutRepository.deleteById(id);
    }
}
