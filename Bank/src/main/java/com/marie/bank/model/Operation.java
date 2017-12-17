package com.marie.bank.model;

import com.marie.bank.enumeration.TypeOperation;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class manages the operations 
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
@Data
@RequiredArgsConstructor
public class Operation {

    private TypeOperation type;
    private final LocalDate date;
    private BigDecimal balance;
    private final BigDecimal amount;

}
