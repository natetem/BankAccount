
package com.marie.bank.repository;

import com.marie.bank.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marie
 */
public interface OperationRepository extends JpaRepository<Operation, Long>  {
    
}
