package com.gestion.atelier.services;

import com.gestion.atelier.mappers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.repository.ReparationsRepository;
import com.gestion.atelier.repository.TypeReparationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.gestion.atelier.models.Reparations;
import com.gestion.atelier.models.TypeReparation;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReparationsService {

    @Autowired
    private ReparationsRepository reparationsRepository;

    @Autowired
    TypeReparationRepository typeReparationRepository;

    @Autowired
    EntityManager entityManager;

    private final ReparationsMapper reparationsMapper = ReparationsMapper.INSTANCE;
    private final TypeReparationMapper typeReparationMapper = TypeReparationMapper.INSTANCE;
    private final ClientsMapper clientsMapper = ClientsMapper.INSTANCE;
    private final TechniciensMapper techniciensMapper = TechniciensMapper.INSTANCE;
    private final StatutMapper statutMapper = StatutMapper.INSTANCE;
    private final OrdinateursMapper ordinateursMapper = OrdinateursMapper.INSTANCE;

    //
    public Reparations correspondance(ReparationsDTO reparationsDTO) {
        Reparations reparations = new Reparations();
        if (reparationsDTO != null) {
            reparations.setDescription(reparationsDTO.getDescription());
            reparations.setOrdinateur(ordinateursMapper.ordinateursDTOToOrdinateurs(reparationsDTO.getOrdinateur()));
            reparations.setClient(clientsMapper.clientsDTOToClients(reparationsDTO.getClient()));
            reparations.setStatut(statutMapper.statutDTOToStatut(reparationsDTO.getStatut()));
            reparations.setDateDebut(reparationsDTO.getDateDebut());
            reparations.setTechnicien(techniciensMapper.techniciensDTOToTechniciens(reparationsDTO.getTechnicien()));
            reparations.setTypeReparation(typeReparationMapper.typeReparationDTOToTypeReparation(reparationsDTO.getTypeReparation()));
            
            if (reparationsDTO.getDateFin() != null) {
                reparations.setDateFin(reparationsDTO.getDateFin());
            }
        }
        return reparations;
    }

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

    public List<ReparationsDTO> getByType(String typeId) throws Exception {
        if (typeId == null) {
            throw new Exception("type reparation null");
        }

        Long idT = Long.parseLong(typeId);
        TypeReparation type = typeReparationRepository.getById(idT);

        List<Reparations> reparations = reparationsRepository.findByTypeReparation(type);
        System.out.println(reparations);
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
    public List<ReparationsDTO> getBetweenDate(String dateDebut, String dateFin) {
        Date startDate = (dateDebut != null && !dateDebut.isEmpty()) ? Date.valueOf(dateDebut) : null;
        Date endDate = (dateFin != null && !dateFin.isEmpty()) ? Date.valueOf(dateFin) : null;
    
        List<Reparations> reparations;
    
        if (startDate != null && endDate != null) {
            reparations = reparationsRepository.findByDateDebutBetween(startDate, endDate);
        } else if (startDate != null) {
            reparations = reparationsRepository.findByDateDebutGreaterThanEqual(startDate);
        } else if (endDate != null) {
            reparations = reparationsRepository.findByDateDebutLessThanEqual(endDate);
        } else {
            reparations = reparationsRepository.findAll();
        }
    
        return reparations.stream()
                          .map(reparationsMapper::reparationsToReparationsDTO)
                          .collect(Collectors.toList());
    }       
    
    public List<ReparationsDTO> getByDate() {
        List<Reparations> reparations = reparationsRepository.getByDate();
        return reparations.stream()
                          .map(reparationsMapper::reparationsToReparationsDTO)
                          .collect(Collectors.toList());
    }

    //
    public ReparationsDTO createReparation(ReparationsDTO reparationsDTO) {
        Reparations reparation = this.correspondance(reparationsDTO);

        reparation.setCommission(reparation.getTypeReparation().getCommission());

        Reparations savedReparation = reparationsRepository.save(reparation);
        return reparationsMapper.reparationsToReparationsDTO(savedReparation);
    }

    //
    public ReparationsDTO updateReparation(Long id, ReparationsDTO reparationsDTO) throws Exception {
        Reparations existingReparation = reparationsRepository.findById(id).orElse(null);
        if (existingReparation == null) {
            throw new Exception("Réparation introuvable pour la mise à jour");
        }
        Reparations reparationToUpdate = this.correspondance(reparationsDTO);
        reparationToUpdate.setId(existingReparation.getId());
        reparationToUpdate.setCommission(existingReparation.getTypeReparation().getCommission());
        
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
