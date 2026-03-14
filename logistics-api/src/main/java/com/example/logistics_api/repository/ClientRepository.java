package com.example.logistics_api.repository;

import com.example.logistics_api.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {

    private final List<Client> clients = new ArrayList<>();
    private Long nextId = 1L;

    public List<Client> findAll() {
        return new ArrayList<>(clients);
    }

    public Optional<Client> findById(Long id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst();
    }

    public Client save(Client client) {
        client.setId(nextId++);
        clients.add(client);
        return client;
    }
}