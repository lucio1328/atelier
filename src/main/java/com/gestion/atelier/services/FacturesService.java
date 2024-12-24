package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.FacturesDTO;
import com.gestion.atelier.mappers.FacturesMapper;
import com.gestion.atelier.repository.FacturesRepository;
import com.gestion.atelier.models.Factures;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturesService {

    @Autowired
    private FacturesRepository facturesRepository;

    private final FacturesMapper facturesMapper = FacturesMapper.INSTANCE;

    //
    public FacturesDTO getById(Long id) throws Exception {
        Factures facture = facturesRepository.findById(id).orElse(null);
        if (facture == null) {
            throw new Exception("Facture introuvable");
        }
        return facturesMapper.facturesToFacturesDTO(facture);
    }

    //
    public List<FacturesDTO> getAll() {
        List<Factures> factures = facturesRepository.findAll();
        return factures.stream()
                       .map(facturesMapper::facturesToFacturesDTO)
                       .collect(Collectors.toList());
    }

    //
    public FacturesDTO createFacture(FacturesDTO facturesDTO) {
        Factures facture = facturesMapper.facturesDTOToFactures(facturesDTO);
        Factures savedFacture = facturesRepository.save(facture);
        return facturesMapper.facturesToFacturesDTO(savedFacture);
    }

    //
    public FacturesDTO updateFacture(Long id, FacturesDTO facturesDTO) throws Exception {
        Factures existingFacture = facturesRepository.findById(id).orElse(null);
        if (existingFacture == null) {
            throw new Exception("Facture introuvable pour la mise à jour");
        }
        Factures factureToUpdate = facturesMapper.facturesDTOToFactures(facturesDTO);
        Factures updatedFacture = facturesRepository.save(factureToUpdate);

        return facturesMapper.facturesToFacturesDTO(updatedFacture);
    }

    //
    public void deleteFacture(Long id) throws Exception {
        Factures facture = facturesRepository.findById(id).orElse(null);
        if (facture == null) {
            throw new Exception("Facture introuvable pour suppression");
        }
        facturesRepository.deleteById(id);
    }
}
