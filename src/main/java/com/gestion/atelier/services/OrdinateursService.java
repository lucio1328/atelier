package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.OrdinateursDTO;
import com.gestion.atelier.mappers.ClientsMapper;
import com.gestion.atelier.mappers.ModelesMapper;
import com.gestion.atelier.mappers.OrdinateursMapper;
import com.gestion.atelier.repository.OrdinateursRepository;
import com.gestion.atelier.models.Ordinateurs;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdinateursService {

    @Autowired
    private OrdinateursRepository ordinateursRepository;

    private final OrdinateursMapper ordinateursMapper = OrdinateursMapper.INSTANCE;
    private final ClientsMapper clientsMapper = ClientsMapper.INSTANCE;
    private final ModelesMapper modelesMapper = ModelesMapper.INSTANCE;

    //
    public OrdinateursDTO getById(Long id) throws Exception {
        Ordinateurs ordinateur = ordinateursRepository.findById(id).orElse(null);
        if (ordinateur == null) {
            throw new Exception("Ordinateur introuvable");
        }
        return ordinateursMapper.ordinateursToOrdinateursDTO(ordinateur);
    }

    //
    public List<OrdinateursDTO> getAll() {
        List<Ordinateurs> ordinateurs = ordinateursRepository.findAll();
        return ordinateurs.stream()
                          .map(ordinateursMapper::ordinateursToOrdinateursDTO)
                          .collect(Collectors.toList());
    }

    //
    public OrdinateursDTO createOrdinateur(OrdinateursDTO ordinateursDTO) {
        Ordinateurs ordinateur = ordinateursMapper.ordinateursDTOToOrdinateurs(ordinateursDTO);
        Ordinateurs savedOrdinateur = ordinateursRepository.save(ordinateur);
        return ordinateursMapper.ordinateursToOrdinateursDTO(savedOrdinateur);
    }

    //
    public OrdinateursDTO updateOrdinateur(Long id, OrdinateursDTO ordinateursDTO) throws Exception {
        Ordinateurs existingOrdinateur = ordinateursRepository.findById(id).orElse(null);
        if (existingOrdinateur == null) {
            throw new Exception("Ordinateur introuvable pour la mise à jour");
        }
    
        existingOrdinateur.setNumeroSerie(ordinateursDTO.getNumeroSerie());
        existingOrdinateur.setDescription(ordinateursDTO.getDescription());
        existingOrdinateur.setClient(clientsMapper.clientsDTOToClients(ordinateursDTO.getClient()));
        existingOrdinateur.setModele(modelesMapper.modelesDTOToModeles(ordinateursDTO.getModele()));
    
        Ordinateurs updatedOrdinateur = ordinateursRepository.save(existingOrdinateur);
    
        return ordinateursMapper.ordinateursToOrdinateursDTO(updatedOrdinateur);
    }    

    //
    public void deleteOrdinateur(Long id) throws Exception {
        Ordinateurs ordinateur = ordinateursRepository.findById(id).orElse(null);
        if (ordinateur == null) {
            throw new Exception("Ordinateur introuvable pour suppression");
        }
        ordinateursRepository.deleteById(id);
    }
}
