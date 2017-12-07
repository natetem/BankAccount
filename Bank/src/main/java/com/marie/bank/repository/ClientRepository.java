
package com.marie.bank.repository;

import com.marie.bank.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marie
 */
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    Client findByUsername(String username);
    
}
