package com.marie.bank.model;

import com.marie.bank.enumeration.TypeOperation;
import java.time.LocalDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class Manage the Operations on account
 *
 * @author <a href="mailto:mariejeanne.natete@gmail.com">Marie Jeanne NATETE</a>
 */
@Data
@RequiredArgsConstructor
public class Operation {

    private final TypeOperation type;
    private final LocalDate date;
    private double balance;
    private final double amount;

}
