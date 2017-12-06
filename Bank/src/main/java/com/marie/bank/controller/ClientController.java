package com.marie.bank.controller;

import com.marie.bank.service.ClientService;
import com.marie.bank.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marie
 */
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<Client> listOfClient() {
        return clientService.findByLastName("Bauer");
    }

    @GetMapping("/client/{id}")
    public Client createClient(@PathVariable Integer id) {
        return clientService.getClient(id);
    }

    @GetMapping("/client/delete/{id}")
    public String deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return "remove client" + id;
    }

    @PostMapping("/client/update/")
    public String updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
        return "update client" + client.getId();

    }

    @PostMapping("/client/create/")
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);

    }

}
