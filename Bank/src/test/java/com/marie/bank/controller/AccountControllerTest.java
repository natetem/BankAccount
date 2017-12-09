package com.marie.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import com.marie.bank.service.AccountService;
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
public class AccountControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getAccountById() throws Exception {
        mvc.perform(get("/accounts/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", equalTo(1)))
                .andExpect(jsonPath("balance", equalTo(600.0)))
                .andExpect(jsonPath("client.id", equalTo(1)));

    }

    @Test
    public void findAllAccounts() throws Exception {
        mvc.perform(get("/accounts"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].balance", equalTo(400.0)))
                .andExpect(jsonPath("$[1].client.username", equalTo("jackb")));

    }

    @Test
    public void deleteAccount() throws Exception {
        mvc.perform(delete("/accounts/3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void createAccount() throws Exception {
        Client client = clientService.getClient(2);
        Account account = new Account(1000, client);
        mvc.perform(post("/accounts")
                .content(objectMapper.writeValueAsString(account))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        
    }
    
      @Test
    public void depositOnAccount() throws Exception {
        mvc.perform(post("/accounts/deposit").param("id", "1").param("amount","200")     
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
    
       @Test
    public void withtdrawalOnAccount() throws Exception {
        mvc.perform(post("/accounts/withdrawal").param("id", "2").param("amount","200")     
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }


}
