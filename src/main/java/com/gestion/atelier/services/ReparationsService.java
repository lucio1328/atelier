package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.mappers.ReparationsMapper;
import com.gestion.atelier.repository.ReparationsRepository;
import com.gestion.atelier.models.Reparations;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReparationsService {

    @Autowired
    private ReparationsRepository reparationsRepository;

    private final ReparationsMapper reparationsMapper = ReparationsMapper.INSTANCE;

    //
    public ReparationsDTO getById(Long id) throws Exception {
        Reparations reparation = reparationsRepository.findById(id).orElse(null);
        if (reparation == null) {
            throw new Exception("Réparation introuvable");
        }
        return reparationsMapper.reparationsToReparationsDTO(reparation);
    }

    //
    public List<ReparationsDTO> getAll() {
        List<Reparations> reparations = reparationsRepository.findAll();
        return reparations.stream()
                          .map(reparationsMapper::reparationsToReparationsDTO)
                          .collect(Collectors.toList());
    }

    //
    public List<ReparationsDTO> getByIdStatut(Long statutId) {
        List<Reparations> reparations = reparationsRepository.getByIdStatut(statutId);
        return reparations.stream()
                          .map(reparationsMapper::reparationsToReparationsDTO)
                          .collect(Collectors.toList());
    }

    //
    public List<ReparationsDTO> getByIdTechnicien(Long technicienId) {
        List<Reparations> reparations = reparationsRepository.getByIdTechnicien(technicienId);
        return reparations.stream()
                          .map(reparationsMapper::reparationsToReparationsDTO)
                          .collect(Collectors.toList());
    }

    //
    public List<ReparationsDTO> getByIdClient(Long clientId) {
        List<Reparations> reparations = reparationsRepository.getByIdClient(clientId);
        return reparations.stream()
                          .map(reparationsMapper::reparationsToReparationsDTO)
                          .collect(Collectors.toList());
    }

    //
    public List<ReparationsDTO> getBetweenDate(Date dateDebut, Date dateFin) {
        List<Reparations> reparations = reparationsRepository.getBetweenDate(dateDebut, dateFin);
        return reparations.stream()
                          .map(reparationsMapper::reparationsToReparationsDTO)
                          .collect(Collectors.toList());
    }

    //
    public ReparationsDTO createReparation(ReparationsDTO reparationsDTO) {
        Reparations reparation = reparationsMapper.reparationsDTOToReparations(reparationsDTO);
        Reparations savedReparation = reparationsRepository.save(reparation);
        return reparationsMapper.reparationsToReparationsDTO(savedReparation);
    }

    //
    public ReparationsDTO updateReparation(Long id, ReparationsDTO reparationsDTO) throws Exception {
        Reparations existingReparation = reparationsRepository.findById(id).orElse(null);
        if (existingReparation == null) {
            throw new Exception("Réparation introuvable pour la mise à jour");
        }
        Reparations reparationToUpdate = reparationsMapper.reparationsDTOToReparations(reparationsDTO);
        Reparations updatedReparation = reparationsRepository.save(reparationToUpdate);

        return reparationsMapper.reparationsToReparationsDTO(updatedReparation);
    }

    //
    public void deleteReparation(Long id) throws Exception {
        Reparations reparation = reparationsRepository.findById(id).orElse(null);
        if (reparation == null) {
            throw new Exception("Réparation introuvable pour suppression");
        }
        reparationsRepository.deleteById(id);
    }

}
