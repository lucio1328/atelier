package com.gestion.atelier.services;

import com.gestion.atelier.DTO.SpecialiteTechnicienDTO;
import com.gestion.atelier.DTO.SpecialitesDTO;
import com.gestion.atelier.DTO.TechniciensDTO;
import com.gestion.atelier.mappers.SpecialiteTechnicienMapper;
import com.gestion.atelier.mappers.SpecialitesMapper;
import com.gestion.atelier.mappers.TechniciensMapper;
import com.gestion.atelier.models.SpecialiteTechnicien;
import com.gestion.atelier.models.Specialites;
import com.gestion.atelier.repository.SpecialiteTechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialiteTechnicienService {

    @Autowired
    private SpecialiteTechnicienRepository specialiteTechnicienRepository;

    @Autowired
    private SpecialitesService specialitesService;

    @Autowired
    private TechniciensService techniciensService;

    private final SpecialiteTechnicienMapper specialiteTechnicienMapper =  SpecialiteTechnicienMapper.INSTANCE;
    private final SpecialitesMapper specialitesMapper =  SpecialitesMapper.INSTANCE;
    private final TechniciensMapper techniciensMapper =  TechniciensMapper.INSTANCE;

    //
    public List<SpecialiteTechnicienDTO> getAll() {
        List<SpecialiteTechnicien> specialiteTechniciens = specialiteTechnicienRepository.getAll();
        return specialiteTechniciens.stream()
                .map(specialiteTechnicienMapper::specialiteTechnicienToSpecialiteTechnicienDTO)
                .collect(Collectors.toList());
    }

    //
    public List<SpecialitesDTO> getByTechnicienId(Long technicienId) {
        List<SpecialiteTechnicien> specialitesTechniciens = specialiteTechnicienRepository.getByTechnicien(technicienId);
        List<Specialites> specialites = new ArrayList<>();
        for (SpecialiteTechnicien spt : specialitesTechniciens) {
            specialites.add(spt.getSpecialite());
        }
        return specialites.stream()
                .map(specialitesMapper::specialitesToSpecialitesDTO)
                .collect(Collectors.toList());
    }

    //
    public SpecialiteTechnicienDTO getBySpecialiteId(Long specialiteId) {
        SpecialiteTechnicien specialiteTechnicien = specialiteTechnicienRepository.getByIdSpecialite(specialiteId);
        return specialiteTechnicienMapper.specialiteTechnicienToSpecialiteTechnicienDTO(specialiteTechnicien);
    }

    //
    public SpecialiteTechnicienDTO save(Long technicienId, Long specialiteId) throws Exception {
        TechniciensDTO techniciensDTO = techniciensService.getById(technicienId);
        SpecialitesDTO specialitesDTO = specialitesService.getSpecialiteById(specialiteId);
        
        SpecialiteTechnicienDTO specialiteTechnicienDTO = new SpecialiteTechnicienDTO(techniciensDTO, specialitesDTO);
        SpecialiteTechnicien specialiteTechnicien = specialiteTechnicienMapper.specialiteTechnicienDTOToSpecialiteTechnicien(specialiteTechnicienDTO);
        specialiteTechnicien = specialiteTechnicienRepository.save(specialiteTechnicien);
        
        return specialiteTechnicienMapper.specialiteTechnicienToSpecialiteTechnicienDTO(specialiteTechnicien);
    }

    //
    public List<SpecialitesDTO> getSpecialitesNonAffectees(Long technicienId, List<SpecialitesDTO> specialitesDTOs) {
        List<SpecialitesDTO> specialitesDTOsTech = new SpecialiteTechnicienService().getByTechnicienId(technicienId);
        List<SpecialitesDTO> specialitesDTOsNonAffectees = new ArrayList<>();
        for (SpecialitesDTO specialiteDTO : specialitesDTOs) {
            boolean exists = false;

            for (SpecialitesDTO specialiteDTOTech : specialitesDTOsTech) {
                if (specialiteDTO.getId().equals(specialiteDTOTech.getId())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                specialitesDTOsNonAffectees.add(specialiteDTO);
            }
        }
    
        return specialitesDTOsNonAffectees;
    }

    //(facultatif)
    // public SpecialiteTechnicienDTO update(Long technicienId, Long specialiteId, SpecialiteTechnicienDTO specialiteTechnicienDTO) {
    //     SpecialiteTechnicien existing = specialiteTechnicienRepository.getByIdTechnicienAndSpecialite(technicienId, specialiteId);

    //     if (existing != null) {
    //         existing.setSpecialite(specialitesMapper.specialitesDTOToSpecialites(specialiteTechnicienDTO.getSpecialite()));
    //         existing.setTechnicien(techniciensMapper.techniciensDTOToTechniciens(specialiteTechnicienDTO.getTechnicien()));
    //         specialiteTechnicienRepository.save(existing);
    //         return specialiteTechnicienMapper.specialiteTechnicienToSpecialiteTechnicienDTO(existing);
    //     } 
    //     else {
    //         return null;
    //     }
    // }

    // //
    // public void delete(Long technicienId, Long specialiteId) {
    //     SpecialiteTechnicien specialiteTechnicien = specialiteTechnicienRepository.getByIdTechnicienAndSpecialite(technicienId, specialiteId);
    //     if (specialiteTechnicien != null) {
    //         specialiteTechnicienRepository.delete(specialiteTechnicien);
    //     }
    // }
}
