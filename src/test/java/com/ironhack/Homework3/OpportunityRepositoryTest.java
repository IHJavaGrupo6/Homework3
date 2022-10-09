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
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OpportunityRepositoryTest {
    SalesRep salesRep;
    SalesRep salesRep2;
    Lead lead;
    Lead lead2;
    Lead lead3;
    Opportunity opportunity;
    Opportunity opportunity2;
    Opportunity opportunity3;
    Opportunity opportunity4;

    Account account;
    Account account2;
    List<Lead> leadList;
    List<Lead> leadList2;
    List<Opportunity> opportunityList;
    List<Opportunity> opportunityList2;
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
        opportunityList2 = new ArrayList<>();
        contactList = new ArrayList<>();


        salesRep = salesRepRepository.save(new SalesRep(1L,"Jaume",leadList,opportunityList));
        lead = leadRepository.save(new Lead("Quim",999888777,"mail@mail.com","Patata",salesRep));
        lead2 = leadRepository.save(new Lead("Quim2",999888777,"mail@mail.com","Patata",salesRep));
        lead3 = leadRepository.save(new Lead("Quim3",999888777,"mail@mail.com","Patata"));
        contactList.add(Utilities.newContact(lead));
        account = accountRepository.save(new Account(Industry.ECOMMERCE,200L,"BCN","ESP",contactList,opportunityList));
        account2= accountRepository.save(new Account(Industry.ECOMMERCE,200L,"BCN","FRA",contactList,opportunityList2));
        opportunity = opportunityRepository.save(new Opportunity(20L, Product.BOX, Utilities.newContact(lead), account, salesRep));
        opportunity2 = opportunityRepository.save(new Opportunity(50L, Product.BOX, Utilities.newContact(lead), account, salesRep));
        opportunity3 = new Opportunity(30L, Product.FLATBED, Utilities.newContact(lead), account2, salesRep);
        opportunity3.setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunity3);
        opportunity4 = new Opportunity(80L, Product.HYBRID, Utilities.newContact(lead), account2, salesRep);
        opportunity4.setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunity4);
        opportunityList.add(opportunity);
        opportunityList.add(opportunity2);
        opportunityList2.add(opportunity3);
        opportunityList2.add(opportunity4);
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
    @DisplayName("Count Opportunities Grouped by Country works ok")
    void countOpportunitiesByCountry_works_Ok(){
        List<Object[]> optionalList = opportunityRepository.countOpportunitiesByCountry();
        Assertions.assertEquals("ESP",optionalList.get(0)[0].toString());
        Assertions.assertEquals("2",optionalList.get(0)[1].toString());
        Assertions.assertEquals("FRA",optionalList.get(1)[0].toString());
        Assertions.assertEquals("2",optionalList.get(1)[1].toString());


    }


    @Test
    @DisplayName("Count Opportunities Grouped by Country Where Status=Open works Ok")
    void countOpportunitiesByCountryWhereStatusLikeOpen_works_Ok(){
        List<Object[]> optionalList = opportunityRepository.countOpportunitiesByCountryWhereStatusLikeOpen();
        Assertions.assertEquals("OPEN",optionalList.get(0)[0].toString());
        Assertions.assertEquals("2",optionalList.get(0)[1].toString());
        Assertions.assertEquals("ESP",optionalList.get(0)[2].toString());
    }

    @Test
    @DisplayName("Count Opportunities Grouped by Country Where Status=Won works Ok")
    void countOpportunitiesByCountryWhereStatusLikeWon_works_Ok(){
        List<Object[]> optionalList = opportunityRepository.countOpportunitiesByCountryWhereStatusLikeWon();
        Assertions.assertEquals("CLOSED_WON",optionalList.get(0)[0].toString());
        Assertions.assertEquals("1",optionalList.get(0)[1].toString());
        Assertions.assertEquals("FRA",optionalList.get(0)[2].toString());

    }

    @Test
    @DisplayName("Count Opportunities Grouped by Country Where Status=Lost works Ok")
    void countOpportunitiesByCountryWhereStatusLikeLost_works_Ok(){
        List<Object[]> optionalList = opportunityRepository.countOpportunitiesByCountryWhereStatusLikeLost();
        Assertions.assertEquals("CLOSED_LOST",optionalList.get(0)[0].toString());
        Assertions.assertEquals("1",optionalList.get(0)[1].toString());
        Assertions.assertEquals("FRA",optionalList.get(0)[2].toString());
    }
}
