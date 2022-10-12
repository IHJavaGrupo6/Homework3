package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {

    @Query("SELECT sr.name, COUNT(l) FROM SalesRep sr JOIN sr.leads l GROUP BY sr.name")
    List<Object[]> countLeadsBySalesRep();

    @Query("SELECT sr.name, COUNT(o) FROM SalesRep sr JOIN sr.opportunities o GROUP BY sr.name")
    List<Object[]> countOpportunitiesBySalesRep();


    @Query("SELECT sr.name, COUNT(op) FROM SalesRep  sr JOIN sr.opportunities op WHERE op.status = 'CLOSED_WON' GROUP BY sr.name")
    List<Object[]> findCountByStatusClosedWon();

    @Query("SELECT sr.name, COUNT(o) FROM SalesRep  sr JOIN sr.opportunities o WHERE o.status LIKE 'CLOSED_LOST' GROUP BY sr.name")
    List<Object[]> findCountByStatusClosedLost();

    @Query("SELECT sr.name, COUNT(o) FROM SalesRep  sr JOIN sr.opportunities o WHERE o.status LIKE 'OPEN' GROUP BY sr.name")
    List<Object[]> findCountByStatusOpen();
}
