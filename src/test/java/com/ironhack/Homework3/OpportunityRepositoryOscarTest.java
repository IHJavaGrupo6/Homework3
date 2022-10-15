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
public class OpportunityRepositoryOscarTest {

    SalesRep salesRep;
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
    void setUp() {
        leadList = new ArrayList<>();
        opportunityList = new ArrayList<>();
        opportunityList2 = new ArrayList<>();
        contactList = new ArrayList<>();

        salesRep = salesRepRepository.save(new SalesRep("Jaume", leadList, opportunityList));
        lead = leadRepository.save(new Lead("Quim", 999888777, "mail@mail.com", "Patata", salesRep));
        lead2 = leadRepository.save(new Lead("Quim2", 999888777, "mail@mail.com", "Patata", salesRep));
        lead3 = leadRepository.save(new Lead("Quim3", 999888777, "mail@mail.com", "Patata"));
        contactList.add(Utilities.newContact(lead));
        account = accountRepository.save(new Account(Industry.ECOMMERCE, 200L, "BCN", "ESP", contactList, opportunityList));
        account2 = accountRepository.save(new Account(Industry.ECOMMERCE, 200L, "BCN", "FRA", contactList, opportunityList2));
        opportunity = opportunityRepository.save(new Opportunity(20L, Product.BOX, Utilities.newContact(lead), account, salesRep));
        opportunity2 = opportunityRepository.save(new Opportunity(50L, Product.BOX, Utilities.newContact(lead), account2, salesRep));
        opportunity3 = new Opportunity(30L, Product.FLATBED, Utilities.newContact(lead), account2, salesRep);
        opportunity3.setStatus(Status.CLOSED_WON);
        opportunityRepository.save(opportunity3);
        opportunity4 = new Opportunity(80L, Product.HYBRID, Utilities.newContact(lead), account2, salesRep);
        opportunity4.setStatus(Status.CLOSED_LOST);
        opportunityRepository.save(opportunity4);
        opportunityList.add(opportunity);
        opportunityList2.add(opportunity2);
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
    @DisplayName("The mean number of Opps associated with an Account")
    void meanNumberOpportunitiesAssociatedAccount_works() {
        Double mean = opportunityRepository.meanOpportunitiesAccount();
        Assertions.assertEquals(Double.valueOf(2), mean);
    }

    @Test
    @DisplayName("The max number of Opps associated with an Account")
    void maxNumberOpportunitiesAssociatedAccount_works() {
        Long max = opportunityRepository.maxOpportunitiesAccount();
        Assertions.assertEquals(Long.valueOf(3), max);
    }

    @Test
    @DisplayName("The min number of Opps associated with an Account")
    void minNumberOpportunitiesAssociatedAccount_works() {
        Long min = opportunityRepository.minOpportunitiesAccount();
        Assertions.assertEquals(Long.valueOf(1), min);
    }

    @Test
    @DisplayName("The median number of Opps associated with an Account")
    void medianNumberOpportunitiesAssociatedAccount_works() {
        Double median = opportunityRepository.medianOpportunitiesAccount();
        Assertions.assertEquals(Double.valueOf(12), median);
    }

    @Test
    @DisplayName("Shows a count of all Opportunities by the product")
    void findCountByProduct_works() {
        List<Object[]> productList = opportunityRepository.findCountByProduct();
        Assertions.assertEquals((long)2, productList.get(0)[0]);
    }

    @Test
    @DisplayName("The mean quantity of products order")
    void averageQuantityOfProducts_works() {
        Double average = opportunityRepository.averageQuantityOfProducts();
        Assertions.assertEquals(Double.valueOf(45), average);
    }

    @Test
    @DisplayName("The maximum quantity of products")
    void maxQuantityOfProducts_works() {
        Long max = opportunityRepository.maxQuantityOfProducts();
        Assertions.assertEquals(Long.valueOf(80), max);
    }

    @Test
    @DisplayName("The minimum quantity of products")
    void minQuantityOfProducts_works() {
        Long min = opportunityRepository.minQuantityOfProducts();
        Assertions.assertEquals(Long.valueOf(20), min);
    }

    @Test
    @DisplayName("The median quantity of products")
    void medianQuantityOfProducts_works() {
        Double median = opportunityRepository.medianQuantityOfProducts();
        Assertions.assertEquals(Double.valueOf(40), median);
    }
}