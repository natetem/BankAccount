package com.marie.bank.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest {

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getClientById() throws Exception {

        mockServer.expect(manyTimes(), requestTo("/clients/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{ \"id\" : \"1\", \"username\" : \"jackb\", \"firstName\" : \"Jack\", \"lastName\" : \"Bauer\", \"password\" : \"123.\"}", MediaType.APPLICATION_JSON));
        mockServer.verify();
    }

}
