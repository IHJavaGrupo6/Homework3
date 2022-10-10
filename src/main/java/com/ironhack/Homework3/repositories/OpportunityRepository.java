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
   // @Query("Select count(id) From Opportunity group by SalesRep")
  //  List<Object[]> findCountBySalesRep();

    //A count of CLOSED_WON Opportunities by SalesRep
   // @Query("Select count(id) From Opportunity where status like "CLOSED_WON"")
   // List<Object[]> findCountByStatus("CLOSED_WON");

    //A count of CLOSED_LOST Opportunities by SalesRep
   // @Query("Select count(id) From Opportunity where status like "CLOSED_LOST"")
  //  List<Object[]> findCountByStatus("CLOSED_LOST");

    //A count of OPEN Opportunities by SalesRep
  //  @Query("Select count(id) From Opportunity where status like "OPEN"")
    //List<Object[]> findCountByStatus("OPEN");

    //by Product
    //A count of all Opportunities by the product
  //  @Query("Select count(id) From Opportunity group by product")
  //  List<Object[]> findCountByProduct();

    //A count of all CLOSED_WON Opportunities
   // @Query("Select count(id) From Opportunity where status like "CLOSED_WON" group by product)
   // List<Object[]> findCountByProduct();

    //A count of all CLOSED_LOST Opportunities
//            @Query("Select count(id) From Opportunity where status like "CLOSED_WON" group by product)
//                    List<Object[]> findCountByProduct();

*/
}
