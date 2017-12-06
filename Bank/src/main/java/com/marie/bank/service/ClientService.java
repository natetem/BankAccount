package com.marie.bank.service;

import com.marie.bank.repository.ClientRepository;
import com.marie.bank.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClient(int id) {
        return clientRepository.findOne(id);
    }

    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.delete(id);
    }

    public List<Client> findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }
}
