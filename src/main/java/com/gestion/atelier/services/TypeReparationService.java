package com.gestion.atelier.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.TypeReparationDTO;
import com.gestion.atelier.mappers.TypeReparationMapper;
import com.gestion.atelier.models.TypeReparation;
import com.gestion.atelier.repository.TypeReparationRepository;


@Service
public class TypeReparationService {
    
    @Autowired
    private TypeReparationRepository typeReparationRepository;

    private final TypeReparationMapper typeReparationMapper = TypeReparationMapper.INSTANCE;

    public List<TypeReparationDTO> getAll(){
        List<TypeReparation> typeReparations = typeReparationRepository.findAll();
        return typeReparations.stream()
                                .map(typeReparationMapper::typeReparationToTypeReparationDTO)
                                .collect(Collectors.toList());
    }

    public TypeReparationDTO getById(Long idType) {
        return typeReparationMapper.typeReparationToTypeReparationDTO(typeReparationRepository.getTypeReparationById(idType));
    }

}
