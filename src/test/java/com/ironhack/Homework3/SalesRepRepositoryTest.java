package com.ironhack.Homework3;

import com.ironhack.Homework3.models.Lead;
import com.ironhack.Homework3.models.Opportunity;
import com.ironhack.Homework3.models.SalesRep;
import com.ironhack.Homework3.repositories.LeadRepository;
import com.ironhack.Homework3.repositories.OpportunityRepository;
import com.ironhack.Homework3.repositories.SalesRepRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SalesRepRepositoryTest {
    SalesRep salesRep;
    SalesRep salesRep2;

    Lead lead;
    Lead lead2;
    Lead lead3;
    List<Lead> leadList;
    List<Lead> leadList2;
    List<Opportunity> opportunityList;
    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    SalesRepRepository salesRepRepository;

    @BeforeEach
    void setUp(){
        leadList = new ArrayList<>();
        opportunityList = new ArrayList<>();


        salesRep = salesRepRepository.save(new SalesRep(1L,"Jaume",leadList,opportunityList));
        lead = leadRepository.save(new Lead("Quim",999888777,"mail@mail.com","Patata",salesRep));
        lead2 = leadRepository.save(new Lead("Quim2",999888777,"mail@mail.com","Patata",salesRep));
        lead3 = leadRepository.save(new Lead("Quim3",999888777,"mail@mail.com","Patata"));

        leadList.add(lead);
        leadList.add(lead2);


    }


    @AfterEach
    void tearDown(){
        //salesRepRepository.deleteAll();
        //leadRepository.deleteAll();
        //opportunityRepository.deleteAll();

    }

    @Test
    void countLeadsBySalesRep_works(){

        List<Object[]> optionalList = salesRepRepository.countLeadsBySalesRep();
        Assertions.assertEquals(2L,optionalList.get(0)[1]);
        Assertions.assertEquals("Jaume",optionalList.get(0)[0]);



    }

}
