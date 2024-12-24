package com.gestion.atelier.services;

import com.gestion.atelier.DTO.SpecialiteTechnicienDTO;
import com.gestion.atelier.mappers.SpecialiteTechnicienMapper;
import com.gestion.atelier.models.SpecialiteTechnicien;
import com.gestion.atelier.repository.SpecialiteTechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialiteTechnicienService {

    private final SpecialiteTechnicienRepository specialiteTechnicienRepository;

    private final SpecialiteTechnicienMapper specialiteTechnicienMapper;

    @Autowired
    public SpecialiteTechnicienService(SpecialiteTechnicienRepository specialiteTechnicienRepository,
                                       SpecialiteTechnicienMapper specialiteTechnicienMapper) {
        this.specialiteTechnicienRepository = specialiteTechnicienRepository;
        this.specialiteTechnicienMapper = specialiteTechnicienMapper;
    }

    //
    public List<SpecialiteTechnicienDTO> getAll() {
        List<SpecialiteTechnicien> specialiteTechniciens = specialiteTechnicienRepository.getAll();
        return specialiteTechniciens.stream()
                .map(specialiteTechnicienMapper::specialiteTechnicienToSpecialiteTechnicienDTO)
                .collect(Collectors.toList());
    }

    //
    public SpecialiteTechnicienDTO getByTechnicienId(Long technicienId) {
        SpecialiteTechnicien specialiteTechnicien = specialiteTechnicienRepository.getByIdTechnicien(technicienId);
        return specialiteTechnicienMapper.specialiteTechnicienToSpecialiteTechnicienDTO(specialiteTechnicien);
    }

    //
    public SpecialiteTechnicienDTO getBySpecialiteId(Long specialiteId) {
        SpecialiteTechnicien specialiteTechnicien = specialiteTechnicienRepository.getByIdSpecialite(specialiteId);
        return specialiteTechnicienMapper.specialiteTechnicienToSpecialiteTechnicienDTO(specialiteTechnicien);
    }
}
