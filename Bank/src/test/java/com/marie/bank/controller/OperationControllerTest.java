package com.marie.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marie.bank.model.Account;
import com.marie.bank.model.Operation;
import com.marie.bank.service.AccountService;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author marie
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getOperationById() throws Exception {
        mvc.perform(get("/operations/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", equalTo(1)))
                .andExpect(jsonPath("amount", equalTo(200.0)));

    }

    @Test
    public void depositOperation() throws Exception {
        Account account = accountService.getAccount(1L);
        Operation op = new Operation(200, account);
        mvc.perform(post("/operations/deposit")
                .content(objectMapper.writeValueAsString(op))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    }

}
