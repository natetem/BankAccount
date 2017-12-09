package com.marie.bank.service;

import com.marie.bank.model.Client;
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
   
}
