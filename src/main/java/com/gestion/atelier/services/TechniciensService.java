package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.TechniciensDTO;
import com.gestion.atelier.mappers.TechniciensMapper;
import com.gestion.atelier.repository.TechniciensRepository;
import com.gestion.atelier.models.Techniciens;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechniciensService {

    @Autowired
    private TechniciensRepository techniciensRepository;

    private final TechniciensMapper techniciensMapper = TechniciensMapper.INSTANCE;

    //
    public TechniciensDTO getById(Long id) throws Exception {
        Techniciens technicien = techniciensRepository.getById(id);
        if (technicien == null) {
            throw new Exception("Technicien introuvable");
        }
        return techniciensMapper.techniciensToTechniciensDTO(technicien);
    }

    //
    public List<TechniciensDTO> getAll() {
        List<Techniciens> techniciens = techniciensRepository.getAll();
        return techniciens.stream()
                          .map(techniciensMapper::techniciensToTechniciensDTO)
                          .collect(Collectors.toList());
    }

    public static Double getPourcentage(Double valeur){
        return (valeur*5)/100;
    }

    //
    public TechniciensDTO createTechnicien(TechniciensDTO techniciensDTO) {
        Techniciens technicien = techniciensMapper.techniciensDTOToTechniciens(techniciensDTO);
        Techniciens savedTechnicien = techniciensRepository.save(technicien);
        return techniciensMapper.techniciensToTechniciensDTO(savedTechnicien);
    }

    //
    public TechniciensDTO updateTechnicien(Long id, TechniciensDTO techniciensDTO) throws Exception {
        Techniciens existingTechnicien = techniciensRepository.getById(id);
        if (existingTechnicien == null) {
            throw new Exception("Technicien introuvable pour la mise à jour");
        }

        Techniciens technicienToUpdate = techniciensMapper.techniciensDTOToTechniciens(techniciensDTO);

        Techniciens updatedTechnicien = techniciensRepository.save(technicienToUpdate);

        return techniciensMapper.techniciensToTechniciensDTO(updatedTechnicien);
    }

    //
    public void deleteTechnicien(Long id) throws Exception {
        Techniciens technicien = techniciensRepository.getById(id);
        if (technicien == null) {
            throw new Exception("Technicien introuvable pour suppression");
        }
        techniciensRepository.deleteById(id);
    }
}
