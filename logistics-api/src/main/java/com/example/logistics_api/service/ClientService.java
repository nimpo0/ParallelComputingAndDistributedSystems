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
        applyDtoToClient(client, clientDto);
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " + id + " not found"));

        applyDtoToClient(existingClient, clientDto);
        return clientRepository.save(existingClient);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client with id " + id + " not found"));

        clientRepository.delete(client);
    }

    private void applyDtoToClient(Client client, ClientDto clientDto) {
        client.setFullName(clientDto.getFullName());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
    }
}