package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
/*
    //by SalesRep
    //A count of all Opportunities by SalesRep
    @Query("Select count(id) From Opportunity group by SalesRep")
    List<Object[]> findCountBySalesRep();

    //A count of CLOSED_WON Opportunities by SalesRep
    @Query("Select count(id) From Opportunity where status like "CLOSED_WON"")
    List<Object[]> findCountByStatus("CLOSED_WON");

    //A count of CLOSED_LOST Opportunities by SalesRep
    @Query("Select count(id) From Opportunity where status like "CLOSED_LOST"")
    List<Object[]> findCountByStatus("CLOSED_LOST");

    //A count of OPEN Opportunities by SalesRep
    @Query("Select count(id) From Opportunity where status like "OPEN"")
    List<Object[]> findCountByStatus("OPEN");

    //by Product
    //A count of all Opportunities by the product
    @Query("Select count(id) From Opportunity group by product")
    List<Object[]> findCountByProduct();

    //A count of all CLOSED_WON Opportunities
    @Query("Select count(id) From Opportunity where status like "CLOSED_WON" group by product)
            List<Object[]>findCountByProduct();

            //A count of all CLOSED_LOST Opportunities
            @Query("Select count(id) From Opportunity where status like "CLOSED_WON" group by product)
                    List<Object[]>findCountByProduct();

                    List<Object[]> findCountByProduct();

        @Query("SELECT sr.name, COUNT(o) FROM SalesRep sr JOIN sr.opportunities o GROUP BY sr.name")
    List<Object[]> countOpportunitiesBySalesRep();

*/
    //Queries grouped by COUNTRY
    @Query("SELECT a.country, COUNT(op) FROM Opportunity op JOIN op.accountId a GROUP BY a.country")
    List<Object[]> countOpportunitiesByCountry();

    @Query("SELECT op.status, COUNT(op), a.country FROM Opportunity op JOIN op.accountId a WHERE op.status LIKE 0 GROUP BY a.country")
    List<Object[]> countOpportunitiesByCountryWhereStatusLikeOpen();

    @Query("SELECT op.status, COUNT(op), a.country FROM Opportunity op JOIN op.accountId a WHERE op.status LIKE 1 GROUP BY a.country")
    List<Object[]> countOpportunitiesByCountryWhereStatusLikeWon();

    @Query("SELECT op.status, COUNT(op), a.country FROM Opportunity op JOIN op.accountId a WHERE op.status LIKE 2 GROUP BY a.country")
    List<Object[]> countOpportunitiesByCountryWhereStatusLikeLost();

    //Queries grouped by PRODUCT
    @Query("SELECT op.product, COUNT(op) FROM Opportunity op GROUP BY op.product")
    List<Object[]> countOpportunitiesByProduct();

    @Query("SELECT op.status, COUNT(op), op.product FROM Opportunity op WHERE op.status LIKE 0 GROUP BY op.product")
    List<Object[]> countOpportunitiesByProductWhereStatusLikeOpen();

    @Query("SELECT op.status, COUNT(op), op.product FROM Opportunity op WHERE op.status LIKE 1 GROUP BY op.product")
    List<Object[]> countOpportunitiesByProductWhereStatusLikeWon();

    @Query("SELECT op.status, COUNT(op), op.product FROM Opportunity op WHERE op.status LIKE 2 GROUP BY op.product")
    List<Object[]> countOpportunitiesByProductWhereStatusLikeLost();

    //The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
    @Query(value = "SELECT AVG(opportunity.opp) FROM (SELECT COUNT(account_id) as opp FROM homework3.opportunity GROUP BY opportunity.account_id) as opportunity", nativeQuery = true)
    Double meanOpportunitiesAccount();

    //The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account”
    @Query(value = "SELECT MAX(opportunity.opp) FROM (SELECT COUNT(account_id) as opp FROM homework3.opportunity GROUP BY opportunity.account_id) as opportunity", nativeQuery = true)
    Long maxOpportunitiesAccount();

    //The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”
    @Query(value = "SELECT MIN(opportunity.opp) FROM (SELECT COUNT(account_id) as opp FROM homework3.opportunity GROUP BY opportunity.account_id) as opportunity", nativeQuery = true)
    Long minOpportunitiesAccount();

    //The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”
}
