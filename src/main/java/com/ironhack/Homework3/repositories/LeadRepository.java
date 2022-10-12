package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    //By SalesRep
    //A count of Leads by SalesRep
// dublicate in SalesRepRepository
   // @Query("SELECT sr.name, COUNT(l) FROM Lead l JOIN l.salesRepId sr GROUP BY l.salesRepId")
   //List<Object[]> countLeadsBySalesRep();



}
