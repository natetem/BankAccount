package com.marie.bank;

import com.marie.bank.service.ClientService;
import com.marie.bank.model.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(ClientService repository) {
        return (args) -> {
            // save a couple of clients
            repository.createClient(new Client("Jack", "Bauer"));
            repository.createClient(new Client("Chloe", "O'Brian"));
            repository.createClient(new Client("Kim", "Bauer"));
            repository.createClient(new Client("David", "Palmer"));
            repository.createClient(new Client("Michelle", "Dessler"));
        };
    }

}
