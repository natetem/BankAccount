package com.marie.bank.controller;

import com.marie.bank.service.ClientService;
import com.marie.bank.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marie
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> listOfClient() {
        return clientService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client getClient(@PathVariable Integer id) {
        return clientService.getClient(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

}
