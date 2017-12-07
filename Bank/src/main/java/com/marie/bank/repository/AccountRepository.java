
package com.marie.bank.repository;

import com.marie.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marie
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}

