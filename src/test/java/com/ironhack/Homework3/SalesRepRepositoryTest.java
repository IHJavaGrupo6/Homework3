package com.ironhack.Homework3;

import com.ironhack.Homework3.enums.Industry;
import com.ironhack.Homework3.enums.Product;
import com.ironhack.Homework3.enums.Status;
import com.ironhack.Homework3.models.*;
import com.ironhack.Homework3.repositories.AccountRepository;
import com.ironhack.Homework3.repositories.LeadRepository;
import com.ironhack.Homework3.repositories.OpportunityRepository;
import com.ironhack.Homework3.repositories.SalesRepRepository;
import com.ironhack.Homework3.utilities.Utilities;
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
    Opportunity opportunity;
    Opportunity opportunity2;

    Account account;
    List<Lead> leadList;
    List<Lead> leadList2;
    List<Opportunity> opportunityList;
    List<Contact> contactList;
    @Autowired
    OpportunityRepository opportunityRepository;

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    SalesRepRepository salesRepRepository;
    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void setUp(){
        leadList = new ArrayList<>();
        opportunityList = new ArrayList<>();
        contactList = new ArrayList<>();


        salesRep = salesRepRepository.save(new SalesRep(1L,"Jaume",leadList,opportunityList));
        lead = leadRepository.save(new Lead("Quim",999888777,"mail@mail.com","Patata",salesRep));
        lead2 = leadRepository.save(new Lead("Quim2",999888777,"mail@mail.com","Patata",salesRep));
        lead3 = leadRepository.save(new Lead("Quim3",999888777,"mail@mail.com","Patata"));
        contactList.add(Utilities.newContact(lead));
        account = accountRepository.save(new Account(Industry.ECOMMERCE,200L,"BCN","ESP",contactList,opportunityList));
        opportunity = opportunityRepository.save(new Opportunity(20L, Product.BOX, Utilities.newContact(lead), account, salesRep));

        opportunityList.add(opportunity);
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
