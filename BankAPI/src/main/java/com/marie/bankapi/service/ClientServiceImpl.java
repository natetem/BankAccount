
package com.marie.bankapi.service;

import com.marie.bankapi.model.Client;
import com.marie.bankapi.repository.ClientRepository;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author marie
 */
@Service(value="clientService")
public class ClientServiceImpl implements ClientService {
    @Resource
    private ClientRepository clientRepository;

    @Override
    public Collection<Client> getAllClients() {
        return clientRepository.findAll();
        
    }

    @Override
    public Client getClientById(Long id) {
        return this.clientRepository.findOne(id);
    }

    @Override
    public Client createClient(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
       this.clientRepository.delete(id);
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    
}
