package com.marie.bank.service;

import com.marie.bank.repository.ClientRepository;
import com.marie.bank.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClient(int id) {
        return clientRepository.findOne(id);
    }

}
