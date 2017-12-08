package com.marie.bank.service;

import com.marie.bank.model.Client;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author marie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
     private ClientService clientService;

    @Test
    public void getClientOne() {
        Client client = clientService.getClient(1);
        Assert.assertNotNull(client);
        assertThat(client.getFirstName()).isEqualTo("Jack");

    }


    @Test
    public void findAllClients() {
        List<Client> clients = clientService.findAll();
        assertThat(clients.size()).isGreaterThan(2);
    }

    @Test
    public void createClientNatete() {
        Client expClient = new Client("natetem", "Marie", "Natete", "123.");
        Client client = clientService.createClient(expClient);
        Assert.assertEquals(expClient.getUsername(), client.getUsername());
    }

    @Test
    public void updateClientJack() {
        Client expClient = new Client("jackb", "Jack", "Bauer", "12345.");
        Client client = clientService.updateClient(expClient);
        Assert.assertEquals(expClient.getPassword(), client.getPassword());
    }

    @Test
    public void deleteClientFive() {
        clientService.deleteClient(5);
        Client client = clientService.getClient(5);
        Assert.assertNull(client);
    }

}
