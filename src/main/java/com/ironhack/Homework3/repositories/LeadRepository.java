package com.ironhack.Homework3.repositories;

import com.ironhack.Homework3.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
/*
    //By SalesRep
    //A count of Leads by SalesRep
<<<<<<< HEAD
    //@Query("Select count(name) From Leads group by salesRep")
   //List<Name[]> FindCountByNameLeads();
=======
    @Query("Select count(name) From Leads group by salesRep")
    List<Name[]> FindCountByNameLeads();
    */


>>>>>>> 1cfb4acdffdf7ded8caae6d8984ec33fa7e77c33

Integer countBySalesRep


}
