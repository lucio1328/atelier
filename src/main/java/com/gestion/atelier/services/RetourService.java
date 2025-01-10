package com.gestion.atelier.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.ReparationsDTO;
import com.gestion.atelier.DTO.RetourDTO;
import com.gestion.atelier.mappers.RetourMapper;
import com.gestion.atelier.models.Reparations;
import com.gestion.atelier.models.Retour;
import com.gestion.atelier.repository.RetourRepository;

@Service
public class RetourService {

    @Autowired
    private RetourRepository retourRepository;

    private final RetourMapper retourMapper = RetourMapper.INSTANCE;


    public List<RetourDTO> getAll() {
        List<Retour> retours = retourRepository.getAll();
        return retours.stream()
                          .map(retourMapper::retourToRetourDTO)
                          .collect(Collectors.toList());
    }

   public RetourDTO createRetour(RetourDTO retourDTO) {
        if (retourDTO == null) {
            throw new IllegalArgumentException("Le DTO du retour ne peut pas être nul");
        }

        Retour retour = retourMapper.retourDTOToRetour(retourDTO);
        if (retour == null) {
            throw new IllegalStateException("Erreur de conversion du DTO en entité");
        }

        Retour savedRetour = retourRepository.save(retour);
        return retourMapper.retourToRetourDTO(savedRetour);
    }

    public RetourDTO getById(Long idRetour) throws Exception {
        if (idRetour == null) {
            throw new Exception("idRetour null");
        }
        Retour ret = retourRepository.getById(idRetour);
        return retourMapper.retourToRetourDTO(ret);
    }

    public List<RetourDTO> getByCategorieTypeReparation(Long idCat, Long idType) {
        List<Retour> retours = retourRepository.getByCategorieTypeReparation(idCat,idType);
        return retours.stream()
                          .map(retourMapper::retourToRetourDTO)
                          .collect(Collectors.toList());
    }

    //
    public RetourDTO updateRetour(Long id, RetourDTO retoursDTO) throws Exception {
        Retour existRetour = retourRepository.findById(id).orElse(null);
        if (existRetour == null) {
            throw new Exception("Retour introuvable pour la mise à jour");
        }
        Retour retour = retourMapper.retourDTOToRetour(retoursDTO);
        retour.setId(id);
        Retour updatedRetour = retourRepository.save(retour);

        return retourMapper.retourToRetourDTO(updatedRetour);
    }

    //
    public void deleteRetour(Long id) throws Exception {
        Retour retour = retourRepository.findById(id).orElse(null);
        if (retour == null) {
            throw new Exception("Retour introuvable pour suppression");
        }
        retourRepository.deleteById(id);
    }

}
