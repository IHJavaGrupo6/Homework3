package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    //The mean employeeCount can be displayed by typing “Mean EmployeeCount”
    @Query("SELECT AVG(employeeCount) FROM Account")
    BigDecimal meanEmployeeCount();

    //The maximum employeeCount can be displayed by typing “Max EmployeeCount”
    @Query("SELECT MAX(employeeCount) FROM Account")
    Long maxEmployeeCount();

    //The minimum employeeCount can be displayed by typing “Min EmployeeCount”
    @Query("SELECT MIN(employeeCount) FROM Account")
    Long minEmployeeCount();

    //The median employeeCount can be displayed by typing “Median EmployeeCount”
    @Query(value = "SELECT AVG(employee_count) as median FROM(" +
            "SELECT employee_count FROM account as acc ORDER BY acc.employee_count) as query1 " +
            "WHERE (SELECT @id = @id + 1) BETWEEN @employee_count/2.0 and @employee_count/2.0 + 1", nativeQuery = true)
    Long medianEmployeeCount();
}
