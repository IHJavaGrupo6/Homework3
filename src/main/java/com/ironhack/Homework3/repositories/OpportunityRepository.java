package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    //The mean quantity of products order can be displayed
    @Query("SELECT AVG(quantity) FROM Opportunity ")
    List<Object[]> averageQuantityOfProducts();

    //the median quantity of products order
   // @Query("SELECT AVG(mid_vals) as 'median' FROM ("
      //      SELECT  Opportunity.quantity AS 'mid_vals' FROM ("SELECT @row:=@row+1 AS 'row', ))")

    //The maximum quantity of products order
    @Query("SELECT MAX(quantity) FROM Opportunity");
    List<Object[]> maxQuantityOfProducts();

    //The minimum quantity of products order
    @Query("SELECT MIN(quantity) FROM Opportunity");
    List<Object[]> minQuantityOfProducts();

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
