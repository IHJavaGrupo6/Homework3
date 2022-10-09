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

}
