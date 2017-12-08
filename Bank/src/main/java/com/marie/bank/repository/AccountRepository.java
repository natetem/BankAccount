
package com.marie.bank.repository;

import com.marie.bank.model.Account;
import com.marie.bank.model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marie
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    public List<Account> findAccountsByClient( Client client);
}

