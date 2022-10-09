package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    List<Object[]> findCountByProduct();

    //A count of all CLOSED_LOST Opportunities
            @Query("Select count(id) From Opportunity where status like "CLOSED_WON" group by product)
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


}
