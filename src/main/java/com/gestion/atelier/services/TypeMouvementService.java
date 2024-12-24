package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.TypeMouvementDTO;
import com.gestion.atelier.mappers.TypeMouvementMapper;
import com.gestion.atelier.repository.TypeMouvementRepository;
import com.gestion.atelier.models.TypeMouvement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeMouvementService {

    @Autowired
    private TypeMouvementRepository typeMouvementRepository;

    private final TypeMouvementMapper typeMouvementMapper = TypeMouvementMapper.INSTANCE;

    //
    public List<TypeMouvementDTO> getAllTypeMouvements() {
        List<TypeMouvement> typeMouvements = typeMouvementRepository.findAll();
        if (typeMouvements == null || typeMouvements.isEmpty()) {
            return List.of();
        }
        return typeMouvements.stream()
                             .map(typeMouvementMapper::typeMouvementToTypeMouvementDTO)
                             .collect(Collectors.toList());
    }

    //
    public TypeMouvementDTO getTypeMouvementById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du type de mouvement ne peut pas être nul");
        }
        Optional<TypeMouvement> typeMouvementOpt = typeMouvementRepository.findById(id);
        if (typeMouvementOpt.isEmpty()) {
            throw new IllegalArgumentException("Aucun type de mouvement trouvé avec l'ID : " + id);
        }
        return typeMouvementMapper.typeMouvementToTypeMouvementDTO(typeMouvementOpt.get());
    }

    //
    public TypeMouvementDTO createTypeMouvement(TypeMouvementDTO typeMouvementDTO) {
        if (typeMouvementDTO == null) {
            throw new IllegalArgumentException("Le DTO du type de mouvement ne peut pas être nul");
        }

        TypeMouvement typeMouvement = typeMouvementMapper.typeMouvementDTOToTypeMouvement(typeMouvementDTO);
        if (typeMouvement == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        TypeMouvement savedTypeMouvement = typeMouvementRepository.save(typeMouvement);
        return typeMouvementMapper.typeMouvementToTypeMouvementDTO(savedTypeMouvement);
    }

    //
    public TypeMouvementDTO updateTypeMouvement(TypeMouvementDTO typeMouvementDTO) {
        if (typeMouvementDTO == null || typeMouvementDTO.getId() == null) {
            throw new IllegalArgumentException("Le DTO ou l'ID du type de mouvement ne peuvent pas être nuls");
        }
        Optional<TypeMouvement> existingTypeMouvement = typeMouvementRepository.findById(typeMouvementDTO.getId());
        if (existingTypeMouvement.isEmpty()) {
            throw new IllegalStateException("Aucun type de mouvement trouvé pour l'ID spécifié");
        }

        TypeMouvement typeMouvement = typeMouvementMapper.typeMouvementDTOToTypeMouvement(typeMouvementDTO);
        if (typeMouvement == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        TypeMouvement updatedTypeMouvement = typeMouvementRepository.save(typeMouvement);
        return typeMouvementMapper.typeMouvementToTypeMouvementDTO(updatedTypeMouvement);
    }

    //
    public void deleteTypeMouvement(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du type de mouvement ne peut pas être nul");
        }
        Optional<TypeMouvement> existingTypeMouvement = typeMouvementRepository.findById(id);
        if (existingTypeMouvement.isEmpty()) {
            throw new IllegalStateException("Aucun type de mouvement trouvé pour l'ID spécifié");
        }

        typeMouvementRepository.delete(existingTypeMouvement.get());
    }
}
