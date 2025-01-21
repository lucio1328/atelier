package com.gestion.atelier.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.TypeMouvementDTO;
import com.gestion.atelier.DTO.TypeReparationDTO;
import com.gestion.atelier.mappers.TypeReparationMapper;
import com.gestion.atelier.models.TypeMouvement;
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

    public TypeReparationDTO createTypeReparation(TypeReparationDTO typeReparationDTO) {
        if (typeReparationDTO == null) {
            throw new IllegalArgumentException("Le DTO du type de Reparation ne peut pas être nul");
        }

        TypeReparation typeReparation = typeReparationMapper.typeReparationDTOToTypeReparation(typeReparationDTO);
        if (typeReparation == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        TypeReparation savedTypeReparation = typeReparationRepository.save(typeReparation);
        return typeReparationMapper.typeReparationToTypeReparationDTO(savedTypeReparation);
    }

    //
    public TypeReparationDTO updateTypeReparation(TypeReparationDTO typeReparationDTO) {
        if (typeReparationDTO == null || typeReparationDTO.getId() == null) {
            throw new IllegalArgumentException("Le DTO ou l'ID du type de Reparation ne peuvent pas être nuls");
        }
        Optional<TypeReparation> existingTypeReparation = typeReparationRepository.findById(typeReparationDTO.getId());
        if (existingTypeReparation.isEmpty()) {
            throw new IllegalStateException("Aucun type de Reparation trouvé pour l'ID spécifié");
        }

        TypeReparation typeReparation = typeReparationMapper.typeReparationDTOToTypeReparation(typeReparationDTO);
        if (typeReparation == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        TypeReparation updatedTypeReparation = typeReparationRepository.save(typeReparation);
        return typeReparationMapper.typeReparationToTypeReparationDTO(updatedTypeReparation);
    }

    //
    public void deleteTypeReparation(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du type de Reparation ne peut pas être nul");
        }
        Optional<TypeReparation> existingTypeReparation = typeReparationRepository.findById(id);
        if (existingTypeReparation.isEmpty()) {
            throw new IllegalStateException("Aucun type de Reparation trouvé pour l'ID spécifié");
        }

        typeReparationRepository.delete(existingTypeReparation.get());
    }

}
