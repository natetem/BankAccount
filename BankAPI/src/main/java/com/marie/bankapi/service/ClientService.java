
package com.marie.bankapi.service;

import com.marie.bankapi.model.Client;
import java.util.Collection;

/**
 *
 * @author marie
 */
public interface ClientService {
    Collection <Client> getAllClients();
    Client getClientById(Long id);
    Client createClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long id);
}
