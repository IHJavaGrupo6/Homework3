package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {


    // A count of all Opportunities by industry can be displayed by typing “Report Opportunity by Industry”
    @Query("SELECT a.industry, count(o) FROM Account a JOIN a.opportunities o GROUP BY a.industry")
    List<Object[]> countOpportunitiesByIndustry();

    // A count of all CLOSED_WON Opportunities by industry can be displayed by typing “Report CLOSED-WON by Industry”
    @Query("SELECT a.industry, count(o) FROM Account a JOIN a.opportunities o WHERE o.status LIKE 'CLOSED_WON' GROUP BY a.industry")
    List<Object[]> countClosedWonOpportunitiesByIndustry();

    // A count of all CLOSED_LOST Opportunities by industry can be displayed by typing “Report CLOSED-LOST by Industry”
    @Query("SELECT a.industry, count(o) FROM Account a JOIN a.opportunities o WHERE o.status LIKE 'CLOSED_LOST' GROUP BY a.industry")
    List<Object[]> countClosedLostOpportunitiesByIndustry();

    // A count of all OPEN Opportunities by industry can be displayed by typing “Report OPEN by Industry”
    @Query("SELECT a.industry, count(o) FROM Account a JOIN a.opportunities o WHERE o.status LIKE 'OPEN' GROUP BY a.industry")
    List<Object[]> countOpenOpportunitiesByIndustry();

}
