package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    //by Product
    //The mean quantity of products order can be displayed
    @Query("SELECT AVG(quantity) FROM Opportunity ")
    Double averageQuantityOfProducts();

    //The median quantity of products order
    @Query(value = "WITH v AS (SELECT *, COUNT(*) OVER () AS a, row_number() OVER (ORDER BY quantity) as rn FROM Opportunity) SELECT AVG(quantity) FROM v WHERE rn IN (FLOOR((a+1)/2), FLOOR((a+2)/2))", nativeQuery = true)
    Double medianQuantityOfProducts();

    //A count of all Opportunities by the product
    @Query("Select count(id) From Opportunity group by product")
    List<Object[]> findCountByProduct();

    //The maximum quantity of products order
    @Query("SELECT MAX(quantity) FROM Opportunity")
    Long maxQuantityOfProducts();

    //The minimum quantity of products order
    @Query("SELECT MIN(quantity) FROM Opportunity")
    Long minQuantityOfProducts();

    //A count of all Opportunities by CITY
    @Query("SELECT a.city, COUNT(o) FROM Opportunity o JOIN o.accountId a GROUP BY a.city")
    List<Object[]> countOpportunitiesByCity();

    @Query("SELECT a.city, COUNT(o) FROM Opportunity o JOIN o.accountId a  WHERE o.status = com.ironhack.Homework3.enums.Status.CLOSED_WON GROUP BY a.city")
    List<Object[]> countClosedWonOpportunitiesByCity();

    @Query("SELECT a.city, COUNT(o) FROM Opportunity o JOIN o.accountId a  WHERE o.status = com.ironhack.Homework3.enums.Status.CLOSED_LOST GROUP BY a.city")
    List<Object[]> countClosedLostOpportunitiesByCity();

    @Query("SELECT a.city, COUNT(o) FROM Opportunity o JOIN o.accountId a  WHERE o.status = com.ironhack.Homework3.enums.Status.OPEN GROUP BY a.city")
    List<Object[]> countOpenOpportunitiesByCity();

    //Queries grouped by COUNTRY
    @Query("SELECT a.country, COUNT(op) FROM Opportunity op JOIN op.accountId a GROUP BY a.country")
    List<Object[]> countOpportunitiesByCountry();

    @Query("SELECT COUNT(op), a.country FROM Opportunity op JOIN op.accountId a WHERE op.status = 'OPEN' GROUP BY a.country")
    List<Object[]> countOpportunitiesByCountryWhereStatusLikeOpen();

    @Query("SELECT COUNT(op), a.country FROM Opportunity op JOIN op.accountId a WHERE op.status = 'CLOSED_WON' GROUP BY a.country")
    List<Object[]> countOpportunitiesByCountryWhereStatusLikeWon();

    @Query("SELECT COUNT(op), a.country FROM Opportunity op JOIN op.accountId a WHERE op.status = 'CLOSED_LOST' GROUP BY a.country")
    List<Object[]> countOpportunitiesByCountryWhereStatusLikeLost();

    //Queries grouped by PRODUCT
    @Query("SELECT op.product, COUNT(op) FROM Opportunity op GROUP BY op.product")
    List<Object[]> countOpportunitiesByProduct();

    @Query("SELECT op.status, COUNT(op), op.product FROM Opportunity op WHERE op.status = 'OPEN' GROUP BY op.product")
    List<Object[]> countOpportunitiesByProductWhereStatusLikeOpen();

    @Query("SELECT op.status, COUNT(op), op.product FROM Opportunity op WHERE op.status = 'CLOSED_WON' GROUP BY op.product")
    List<Object[]> countOpportunitiesByProductWhereStatusLikeWon();

    @Query("SELECT op.status, COUNT(op), op.product FROM Opportunity op WHERE op.status = 'CLOSED_LOST' GROUP BY op.product")
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
    @Query(value = "WITH v AS (SELECT *, COUNT(*) OVER () AS a, row_number() OVER (ORDER BY account_id) as rn FROM Opportunity) SELECT AVG(account_id) FROM v WHERE rn IN (FLOOR((a+1)/2), FLOOR((a+2)/2))", nativeQuery = true)
    Double medianOpportunitiesAccount();

}

