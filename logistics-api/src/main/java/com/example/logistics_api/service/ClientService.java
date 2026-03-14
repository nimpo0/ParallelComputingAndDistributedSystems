package com.example.logistics_api.service;

import com.example.logistics_api.dto.ClientDto;
import com.example.logistics_api.exception.ResourceNotFoundException;
import com.example.logistics_api.model.Client;
import com.example.logistics_api.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " + id + " not found"));
    }

    public Client createClient(ClientDto clientDto) {
        Client client = new Client();
        client.setFullName(clientDto.getFullName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());

        return clientRepository.save(client);
    }
}