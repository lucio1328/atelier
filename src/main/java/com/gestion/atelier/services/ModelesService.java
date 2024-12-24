package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.ModelesDTO;
import com.gestion.atelier.mappers.ModelesMapper;
import com.gestion.atelier.repository.ModelesRepository;
import com.gestion.atelier.models.Modeles;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelesService {

    @Autowired
    private ModelesRepository modelesRepository;

    private final ModelesMapper modelesMapper = ModelesMapper.INSTANCE;

    //
    public ModelesDTO getById(Long id) throws Exception {
        Modeles modele = modelesRepository.findById(id).orElse(null);
        if (modele == null) {
            throw new Exception("Modèle introuvable");
        }
        return modelesMapper.modelesToModelesDTO(modele);
    }

    //
    public List<ModelesDTO> getAll() {
        List<Modeles> modeles = modelesRepository.findAll();
        return modeles.stream()
                      .map(modelesMapper::modelesToModelesDTO)
                      .collect(Collectors.toList());
    }

    //
    public ModelesDTO createModele(ModelesDTO modelesDTO) {
        Modeles modele = modelesMapper.modelesDTOToModeles(modelesDTO);
        Modeles savedModele = modelesRepository.save(modele);
        return modelesMapper.modelesToModelesDTO(savedModele);
    }

    //
    public ModelesDTO updateModele(Long id, ModelesDTO modelesDTO) throws Exception {
        Modeles existingModele = modelesRepository.findById(id).orElse(null);
        if (existingModele == null) {
            throw new Exception("Modèle introuvable pour la mise à jour");
        }
        Modeles modeleToUpdate = modelesMapper.modelesDTOToModeles(modelesDTO);
        Modeles updatedModele = modelesRepository.save(modeleToUpdate);

        return modelesMapper.modelesToModelesDTO(updatedModele);
    }

    //
    public void deleteModele(Long id) throws Exception {
        Modeles modele = modelesRepository.findById(id).orElse(null);
        if (modele == null) {
            throw new Exception("Modèle introuvable pour suppression");
        }
        modelesRepository.deleteById(id);
    }
}
