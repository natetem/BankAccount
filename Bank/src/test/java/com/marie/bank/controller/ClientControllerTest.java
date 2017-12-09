package com.marie.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marie.bank.model.Client;
import com.marie.bank.service.ClientService;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author marie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    
     @Autowired
    private ClientService clientService;

    @Test
    public void getClientById() throws Exception {
        mvc.perform(get("/clients/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", equalTo(1)))
                .andExpect(jsonPath("username", equalTo("jackb")));

    }

    @Test
    public void findAllClients() throws Exception {
        mvc.perform(get("/clients"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].username", equalTo("kimb")));

    }

//    @Test
//    public void deleteClient() throws Exception {
//        mvc.perform(delete("/clients/3"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//    }

    @Test
    public void createClient() throws Exception {
        Client client = new Client("natetem", "Marie", "Natete", "123.");
        mvc.perform(post("/clients")
                .content(objectMapper.writeValueAsString(client))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    }

    @Test
    public void updateClient() throws Exception {
        Client client = clientService.getClient(1);
        client.setPassword("123456.");
        mvc.perform(put("/clients")
                .content(objectMapper.writeValueAsString(client))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

  

}
