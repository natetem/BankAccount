/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marie.bank.repository;

import com.marie.bank.model.Client;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author marie
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {

    List<Client> findByLastName(String lastName);
}
