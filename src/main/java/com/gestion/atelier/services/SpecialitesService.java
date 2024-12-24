package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.SpecialitesDTO;
import com.gestion.atelier.mappers.SpecialitesMapper;
import com.gestion.atelier.repository.SpecialitesRepository;
import com.gestion.atelier.models.Specialites;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecialitesService {

    @Autowired
    private SpecialitesRepository specialitesRepository;

    private final SpecialitesMapper specialitesMapper = SpecialitesMapper.INSTANCE;

    //
    public List<SpecialitesDTO> getAllSpecialites() {
        List<Specialites> specialites = specialitesRepository.getAll();
        if (specialites == null || specialites.isEmpty()) {
            return List.of();
        }
        return specialites.stream()
                          .map(specialitesMapper::specialitesToSpecialitesDTO)
                          .collect(Collectors.toList());
    }

    //
    public SpecialitesDTO getSpecialiteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la spécialité ne peut pas être nul");
        }
        Optional<Specialites> specialiteOpt = Optional.ofNullable(specialitesRepository.getById(id));
        if (specialiteOpt.isEmpty()) {
            throw new IllegalArgumentException("Aucune spécialité trouvée avec l'ID : " + id);
        }
        return specialitesMapper.specialitesToSpecialitesDTO(specialiteOpt.get());
    }

    //
    public SpecialitesDTO getSpecialiteByLibelle(String libelle) {
        if (libelle == null || libelle.trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de la spécialité ne peut pas être nul ou vide");
        }
        Specialites specialite = specialitesRepository.getByLibelle(libelle);
        if (specialite == null) {
            throw new IllegalArgumentException("Aucune spécialité trouvée avec le libellé : " + libelle);
        }
        return specialitesMapper.specialitesToSpecialitesDTO(specialite);
    }

    //
    public SpecialitesDTO createSpecialite(SpecialitesDTO specialitesDTO) {
        if (specialitesDTO == null) {
            throw new IllegalArgumentException("Le DTO de la spécialité ne peut pas être nul");
        }

        Specialites specialite = specialitesMapper.specialitesDTOToSpecialites(specialitesDTO);
        if (specialite == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        Specialites savedSpecialite = specialitesRepository.save(specialite);
        return specialitesMapper.specialitesToSpecialitesDTO(savedSpecialite);
    }

    //
    public SpecialitesDTO updateSpecialite(SpecialitesDTO specialitesDTO) {
        if (specialitesDTO == null || specialitesDTO.getId() == null) {
            throw new IllegalArgumentException("Le DTO ou l'ID de la spécialité ne peuvent pas être nuls");
        }
        Optional<Specialites> existingSpecialite = specialitesRepository.findById(specialitesDTO.getId());
        if (existingSpecialite.isEmpty()) {
            throw new IllegalStateException("Aucune spécialité trouvée pour l'ID spécifié");
        }

        Specialites specialite = specialitesMapper.specialitesDTOToSpecialites(specialitesDTO);
        if (specialite == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        Specialites updatedSpecialite = specialitesRepository.save(specialite);
        return specialitesMapper.specialitesToSpecialitesDTO(updatedSpecialite);
    }

    //
    public void deleteSpecialite(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la spécialité ne peut pas être nul");
        }
        Optional<Specialites> existingSpecialite = specialitesRepository.findById(id);
        if (existingSpecialite.isEmpty()) {
            throw new IllegalStateException("Aucune spécialité trouvée pour l'ID spécifié");
        }

        specialitesRepository.delete(existingSpecialite.get());
    }
}
