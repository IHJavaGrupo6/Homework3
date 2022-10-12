package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {


      //by SalesRep
    //A count of all Opportunities by SalesRep
    //dublicate in SalesRepRepository
    //  @Query("SELECT sr.name, COUNT(o) FROM Opportunity o JOIN o.salesRepId sr GROUP BY o.salesRepId")
    //  List<Object[]> countOpportunityBySalesRep();


    //The mean quantity of products order can be displayed
    @Query("SELECT AVG(quantity) FROM Opportunity ")
    List<Object[]> averageQuantityOfProducts();

    //the median quantity of products order
   // @Query("SELECT AVG(mid_vals) as 'median' FROM ("
      //      SELECT  Opportunity.quantity AS 'mid_vals' FROM ("SELECT @row:=@row+1 AS 'row', ))")

    //The maximum quantity of products order
    @Query("SELECT MAX(quantity) FROM Opportunity")
    List<Object[]> maxQuantityOfProducts();

    //The minimum quantity of products order
    @Query("SELECT MIN(quantity) FROM Opportunity")
    List<Object[]> minQuantityOfProducts();


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
/*
SELECT AVG(mid_vals) AS 'median' FROM (
 SELECT tab1.MyNumber AS 'mid_vals' FROM
  (
   SELECT @row:=@row+1 AS 'row', a.MyNumber
   FROM dataset AS a, (SELECT @row:=0) AS r
   ORDER BY a.MyNumber
  ) AS tab1,
  (
   SELECT COUNT(*) as 'count'
   FROM dataset x
  ) AS tab2
  WHERE tab1.row >= tab2.count/2 and tab1.row <= ((tab2.count/2) +1)) AS tab3;
  }
 */
