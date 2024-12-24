package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.ClientsDTO;
import com.gestion.atelier.mappers.ClientsMapper;
import com.gestion.atelier.repository.ClientsRepository;
import com.gestion.atelier.models.Clients;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsService {

    @Autowired
    private ClientsRepository clientsRepository;

    private final ClientsMapper clientsMapper = ClientsMapper.INSTANCE;

    //
    public ClientsDTO getById(Long id) throws Exception {
        Clients client = clientsRepository.getById(id);
        if (client == null) {
            throw new Exception("Client introuvable");
        }
        return clientsMapper.clientsToClientsDTO(client);
    }

    //
    public List<ClientsDTO> getAll() {
        List<Clients> clients = clientsRepository.getAll();
        return clients.stream()
                      .map(clientsMapper::clientsToClientsDTO)
                      .collect(Collectors.toList());
    }

    //
    public ClientsDTO createClient(ClientsDTO clientsDTO) {
        Clients client = clientsMapper.clientsDTOToClients(clientsDTO);
        Clients savedClient = clientsRepository.save(client);
        return clientsMapper.clientsToClientsDTO(savedClient);
    }

    //
    public ClientsDTO updateClient(Long id, ClientsDTO clientsDTO) throws Exception {
        Clients existingClient = clientsRepository.getById(id);
        if (existingClient == null) {
            throw new Exception("Client introuvable pour la mise à jour");
        }
        Clients clientToUpdate = clientsMapper.clientsDTOToClients(clientsDTO);

        Clients updatedClient = clientsRepository.save(clientToUpdate);

        return clientsMapper.clientsToClientsDTO(updatedClient);
    }

    //
    public void deleteClient(Long id) throws Exception {
        Clients client = clientsRepository.getById(id);
        if (client == null) {
            throw new Exception("Client introuvable pour suppression");
        }
        clientsRepository.deleteById(id);
    }
}
