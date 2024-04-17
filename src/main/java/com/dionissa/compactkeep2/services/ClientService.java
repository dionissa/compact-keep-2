package com.dionissa.compactkeep2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dionissa.compactkeep2.model.Client;
import com.dionissa.compactkeep2.repository.ClientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public Client editClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findClientsByName(String name) {
        return clientRepository.findByName(name);
    }

    public List<Client> findClientsByNameAndPhoneNumberAndNotes(String name, String phoneNumber, String notes) {
        return clientRepository
                .findByNameContainingIgnoreCaseAndPhoneNumberContainingIgnoreCaseAndNotesContainingIgnoreCase(name,
                        phoneNumber, notes);
    }
}
