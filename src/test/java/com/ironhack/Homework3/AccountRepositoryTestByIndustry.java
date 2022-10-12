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
public class AccountRepositoryTestByIndustry {

    SalesRep salesRep;
    Lead lead;
    Lead lead2;
    Lead lead3;
    Opportunity opportunity;
    Opportunity opportunity2;
    Opportunity opportunity3;
    Opportunity opportunity4;
    Opportunity opportunity5;
    Opportunity opportunity6;
    Opportunity opportunity7;
    Opportunity opportunity8;
    Account account;
    Account account2;
    Account account3;
    Account account4;
    List<Lead> leadList;
    List<Opportunity> opportunityList;
    List<Opportunity> opportunityList2;
    List<Opportunity> opportunityList3;
    List<Opportunity> opportunityList4;
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
    void setUp() {
        leadList = new ArrayList<>();
        opportunityList = new ArrayList<>();
        opportunityList2 = new ArrayList<>();
        opportunityList3 = new ArrayList<>();
        opportunityList4 = new ArrayList<>();
        contactList = new ArrayList<>();

        account = accountRepository.save(new Account(Industry.ECOMMERCE, 200L, "BCN", "ESP", contactList, opportunityList));
        account2 = accountRepository.save(new Account(Industry.MANUFACTURING, 200L, "BCN", "ESP", contactList, opportunityList2));
        account3 = accountRepository.save(new Account(Industry.MEDICAL, 200L, "BCN", "ESP", contactList, opportunityList3));
        account4 = accountRepository.save(new Account(Industry.OTHER, 200L, "BCN", "FRA", contactList, opportunityList4));
        salesRep = salesRepRepository.save(new SalesRep(1L, "Jaume", leadList, opportunityList));
        lead = leadRepository.save(new Lead("Quim", 999888777, "mail@mail.com", "Patata", salesRep));
        lead2 = leadRepository.save(new Lead("Quim2", 999888777, "mail@mail.com", "Patata", salesRep));
        lead3 = leadRepository.save(new Lead("Quim3", 999888777, "mail@mail.com", "Patata"));
        contactList.add(Utilities.newContact(lead));
        opportunity = opportunityRepository.save(new Opportunity(20L, Product.BOX, Utilities.newContact(lead), account, salesRep));
        opportunity2 = opportunityRepository.save(new Opportunity(50L, Product.BOX, Utilities.newContact(lead2), account, salesRep));
        opportunity3 = new Opportunity(30L, Product.FLATBED, Utilities.newContact(lead3), account, salesRep);
        opportunity3.setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunity3);
        opportunity4 = new Opportunity(80L, Product.HYBRID, Utilities.newContact(lead), account2, salesRep);
        opportunity4.setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunity4);
        opportunity5 = new Opportunity(30L, Product.FLATBED, Utilities.newContact(lead2), account3, salesRep);
        opportunity5.setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunity5);
        opportunity6 = new Opportunity(80L, Product.HYBRID, Utilities.newContact(lead3), account3, salesRep);
        opportunity6.setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunity6);
        opportunity7 = new Opportunity(80L, Product.HYBRID, Utilities.newContact(lead3), account3, salesRep);
        opportunity7.setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunity7);
        opportunity8 = opportunityRepository.save(new Opportunity(50L, Product.BOX, Utilities.newContact(lead3), account4, salesRep));
        opportunityList.add(opportunity);
        opportunityList.add(opportunity2);
        opportunityList2.add(opportunity3);
        opportunityList2.add(opportunity4);
        opportunityList3.add(opportunity5);
        opportunityList3.add(opportunity6);
        opportunityList4.add(opportunity7);
        opportunityList4.add(opportunity8);
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
    @DisplayName("Shows count of oppoprtunities by industry")
    void countOpportunitiesByIndustry_works() {
        List<Object[]> optionalList = accountRepository.countOpportunitiesByIndustry();
        Assertions.assertEquals(3L, optionalList.get(0)[1]);
        Assertions.assertEquals(1L, optionalList.get(1)[1]);
        Assertions.assertEquals(Industry.ECOMMERCE, optionalList.get(0)[0]);
    }

    @Test
    @DisplayName("Shows count of all CLOSED_WON Opportunities by industry")
    void countClosedWonOpportunitiesByIndustry_works() {
        List<Object[]> optionalList = accountRepository.countClosedWonOpportunitiesByIndustry();
        Assertions.assertEquals(Industry.ECOMMERCE, optionalList.get(0)[0]);
        Assertions.assertEquals(1L, optionalList.get(0)[1]);
        Assertions.assertEquals(1L, optionalList.get(1)[1]);
    }

    @Test
    @DisplayName("Shows count of all CLOSED_LOST Opportunities by industry")
    void countClosedLostOpportunitiesByIndustry_works() {
        List<Object[]> optionalList = accountRepository.countClosedLostOpportunitiesByIndustry();
        Assertions.assertEquals(Industry.MANUFACTURING, optionalList.get(0)[0]);
        Assertions.assertEquals(1L, optionalList.get(0)[1]);
        Assertions.assertEquals(2L, optionalList.get(1)[1]);
    }

    @Test
    @DisplayName("Shows count of all OPEN Opportunities by industry")
    void countOpenOpportunitiesByIndustry_works() {
        List<Object[]> optionalList = accountRepository.countOpenOpportunitiesByIndustry();
        Assertions.assertEquals(Industry.OTHER, optionalList.get(1)[0]);
        Assertions.assertEquals(2L, optionalList.get(0)[1]);
        Assertions.assertEquals(1L, optionalList.get(1)[1]);
    }

}
