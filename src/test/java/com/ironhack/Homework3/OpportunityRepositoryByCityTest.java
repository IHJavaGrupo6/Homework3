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

@SpringBootTest
public class OpportunityRepositoryByCityTest {
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


        salesRep = salesRepRepository.save(new SalesRep("Jaume",leadList,opportunityList));
        lead = leadRepository.save(new Lead("Quim",999888777,"mail@mail.com","Patata",salesRep));
        lead2 = leadRepository.save(new Lead("Quim2",999888777,"mail@mail.com","Patata",salesRep));
        lead3 = leadRepository.save(new Lead("Quim3",999888777,"mail@mail.com","Patata"));
        contactList.add(Utilities.newContact(lead));
        account = accountRepository.save(new Account(Industry.ECOMMERCE,200L,"BCN","ESP",contactList,opportunityList));
        account2= accountRepository.save(new Account(Industry.MANUFACTURING,200L,"VLC","FRA",contactList,opportunityList2));
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
    void tearDown() {
        leadRepository.deleteAll();
        opportunityRepository.deleteAll();
        accountRepository.deleteAll();
        salesRepRepository.deleteAll();
    }
    @Test
    @DisplayName("Find Opportunities by City")
    void CountOpportunitiesByCity_ResultOk (){
        List<Object[]> optionalList = opportunityRepository.countOpportunitiesByCity();
        Assertions.assertEquals("BCN", optionalList.get(0)[0]);
        Assertions.assertEquals(2L,optionalList.get(0)[1]);
        Assertions.assertEquals("VLC", optionalList.get(1)[0]);
        Assertions.assertEquals(2L,optionalList.get(1)[1]);

    }
    @Test
    @DisplayName("Count Opportunities Grouped by City Where Status=WON works Ok")
    void countOpportunitiesByCityWhereStatusLikeWon_works_Ok() {
        opportunity.setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunity);
        List<Object[]> optionalList = opportunityRepository.countClosedWonOpportunitiesByCity();
        Assertions.assertEquals("BCN", optionalList.get(0)[0]);
        Assertions.assertEquals(1L, optionalList.get(0)[1]);
        Assertions.assertEquals("VLC", optionalList.get(1)[0]);
        Assertions.assertEquals(1L,optionalList.get(1)[1]);

    }
    @Test
    @DisplayName("Count Opportunities Grouped by City Where Status=LOST works Ok")
    void countOpportunitiesByCityWhereStatusLikeLost_works_Ok() {
        opportunity.setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunity);
        List<Object[]> optionalList = opportunityRepository.countClosedLostOpportunitiesByCity();
        Assertions.assertEquals("BCN", optionalList.get(0)[0]);
        Assertions.assertEquals(1L, optionalList.get(0)[1]);
        Assertions.assertEquals("VLC", optionalList.get(1)[0]);
        Assertions.assertEquals(1L,optionalList.get(1)[1]);

    }
    @Test
    @DisplayName("Count Opportunities Grouped by City Where Status=OPEN works Ok")
    void countClosedLostOpportunitiesByCity_works_Ok() {
        opportunity.setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunity);
        List<Object[]> optionalList = opportunityRepository.countOpenOpportunitiesByCity();
        Assertions.assertEquals("BCN", optionalList.get(0)[0]);
        Assertions.assertEquals(1L, optionalList.get(0)[1]);

    }

}
