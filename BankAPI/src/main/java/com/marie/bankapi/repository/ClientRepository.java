package com.marie.bankapi.repository;

import com.marie.bankapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marie
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
