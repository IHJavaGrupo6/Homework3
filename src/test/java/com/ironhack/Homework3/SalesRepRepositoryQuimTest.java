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
public class SalesRepRepositoryQuimTest {
    SalesRep salesRep;
    SalesRep salesRep2;
    Lead lead;
    Lead lead2;
    Lead lead3;
    Opportunity opportunity;
    Opportunity opportunity2;

    Opportunity opportunity3;

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


        salesRep = salesRepRepository.save(new SalesRep("Jaume",leadList,opportunityList));
        lead = leadRepository.save(new Lead("Quim",999888777,"mail@mail.com","Patata",salesRep));
        lead2 = leadRepository.save(new Lead("Quim2",999888777,"mail@mail.com","Patata",salesRep));
        lead3 = leadRepository.save(new Lead("Quim3",999888777,"mail@mail.com","Patata"));
        contactList.add(Utilities.newContact(lead));
        account = accountRepository.save(new Account(Industry.ECOMMERCE,200L,"BCN","ESP",contactList,opportunityList));
        opportunity = opportunityRepository.save(new Opportunity(20L, Product.BOX, Utilities.newContact(lead), account, salesRep));

        opportunity2 = opportunityRepository.save(new Opportunity(30L, Product.BOX, Utilities.newContact(lead), account, salesRep));
        opportunity2.setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunity2);

        opportunity3 = opportunityRepository.save(new Opportunity(40L, Product.BOX, Utilities.newContact(lead), account, salesRep));
        opportunity3.setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunity3);
        opportunityList.add(opportunity);
        opportunityList.add(opportunity2);
        opportunityList.add(opportunity3);

        leadList.add(lead);
        leadList.add(lead2);


    }


    @AfterEach
    void tearDown(){
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        accountRepository.deleteAll();
        salesRepRepository.deleteAll();

    }

    @Test
    void countLeadsBySalesRep_works(){

        List<Object[]> optionalList = salesRepRepository.countLeadsBySalesRep();
        for (int i = 0; i < optionalList.size(); i++) {
            for (int j = 0; j < optionalList.get(i).length; j++) {
                System.out.println(optionalList.get(i)[j]);

            }
        }
        Assertions.assertEquals(2L,optionalList.get(0)[1]);
        Assertions.assertEquals("Jaume",optionalList.get(0)[0]);
    }

    @Test
    void countOpportunitiesBySalesRep_works(){
        List<Object[]> optionalList = salesRepRepository.countOpportunitiesBySalesRep();

       Assertions.assertEquals("Jaume",optionalList.get(0)[0]);
       Assertions.assertEquals(3L,optionalList.get(0)[1]);
    }

    @Test
    void findCountByStatusClosedWon_works() {
        List<Object[]> optionalList = salesRepRepository.findCountByStatusClosedWon();
        Assertions.assertEquals("Jaume",optionalList.get(0)[0]);
        Assertions.assertEquals(1L,optionalList.get(0)[1]);
    }

    @Test
    void findCountByStatusClosedLost_works() {
        List<Object[]> optionalList = salesRepRepository.findCountByStatusClosedLost();
        Assertions.assertEquals("Jaume",optionalList.get(0)[0]);
        Assertions.assertEquals(1L,optionalList.get(0)[1]);
    }

    @Test
    void findCountByStatusOpen_works() {
        List<Object[]> optionalList = salesRepRepository.findCountByStatusOpen();
        Assertions.assertEquals("Jaume",optionalList.get(0)[0]);
        Assertions.assertEquals(1L,optionalList.get(0)[1]);
    }
}
