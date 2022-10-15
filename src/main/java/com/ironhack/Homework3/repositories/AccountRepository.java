package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // A count of all Opportunities by industry can be displayed by typing “Report Opportunity by Industry”
    @Query("SELECT a.industry, count(o) FROM Account a JOIN a.opportunities o GROUP BY a.industry")
    List<Object[]> countOpportunitiesByIndustry();

    // A count of all CLOSED_WON Opportunities by industry can be displayed by typing “Report CLOSED-WON by Industry”
    @Query("SELECT a.industry, count(o) FROM Account a JOIN a.opportunities o WHERE o.status = 'CLOSED_WON' GROUP BY a.industry")
    List<Object[]> countClosedWonOpportunitiesByIndustry();

    // A count of all CLOSED_LOST Opportunities by industry can be displayed by typing “Report CLOSED-LOST by Industry”
    @Query("SELECT a.industry, count(o) FROM Account a JOIN a.opportunities o WHERE o.status = 'CLOSED_LOST' GROUP BY a.industry")
    List<Object[]> countClosedLostOpportunitiesByIndustry();

    // A count of all OPEN Opportunities by industry can be displayed by typing “Report OPEN by Industry”
    @Query("SELECT a.industry, count(o) FROM Account a JOIN a.opportunities o WHERE o.status = 'OPEN' GROUP BY a.industry")
    List<Object[]> countOpenOpportunitiesByIndustry();


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
    @Query(value = "WITH v AS (SELECT *, COUNT(*) OVER () AS a, row_number() OVER (ORDER BY employee_count) as rn FROM Account) SELECT AVG(employee_count) FROM v WHERE rn IN (FLOOR((a+1)/2), FLOOR((a+2)/2))", nativeQuery = true)
    Long medianEmployeeCount();
}
