package com.ironhack.Homework3.utilities;

import com.ironhack.Homework3.enums.Product;
import com.ironhack.Homework3.models.*;
import com.ironhack.Homework3.repositories.AccountRepository;
import com.ironhack.Homework3.repositories.LeadRepository;
import com.ironhack.Homework3.repositories.OpportunityRepository;
import com.ironhack.Homework3.repositories.SalesRepRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;

public class Utilities {
//    private static Map<Long, Lead> leadMap = new HashMap<>();
//    private static List<Contact> totalContacts = new ArrayList<>();
//    private static List<Opportunity> totalOpportunities = new ArrayList<>();
//    private static List<Account> totalAccounts = new ArrayList<>();
//    private static List<SalesRep> totalSalesReps = new ArrayList<>();
    public static final Pattern VALID_PHONENUMBER_REGEX =
            Pattern.compile("\\A[0-9]{9}\\z", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static String getAnswer(String question) {
        Scanner input = new Scanner(System.in);
        System.out.println(question);
        String answer = input.nextLine();
        if (answer.isBlank() || answer.replaceAll("\\d+", "").isBlank()) {
            throw new IllegalArgumentException("No text received. Please enter at least one letter!");
        }
        return answer;
    }

    public static long getNumber(String question) {
        Scanner input = new Scanner(System.in);
        System.out.println(question);
        String numberString = input.nextLine().replaceAll("\\D+", "");
        if (numberString.isBlank()) {
            throw new IllegalArgumentException("No numbers received. Please enter at least one number!");
        }
        return parseLong(numberString);
    }

    public static Lead newLead(String name, long phoneNumber, String email, String company, Long salesRepId, SalesRepRepository salesRepRepository) {
        if (!validatePhone(String.valueOf(phoneNumber)))
            throw new IllegalArgumentException("Invalid phone format");
        if (!validate(email)) throw new IllegalArgumentException("Invalid email format");
        if (!salesRepRepository.existsById(salesRepId))
            throw new IllegalArgumentException("No SalesRep found with this id");
        Lead lead = new Lead(name, phoneNumber, email, company, salesRepRepository.findById(salesRepId).get());
        System.out.println("New lead created: ");
        return lead;
    }

    public static void convertLead(long id, LeadRepository leadRepository, OpportunityRepository opportunityRepository, AccountRepository accountRepository) {
        // step 1: fetching the lead
        if (!leadRepository.existsById(id)) throw new IllegalArgumentException("No lead found with this id!");
        Lead lead = leadRepository.findById(id).get();
        // step 2: creating a contact
        Contact contact;
        contact = newContact(lead);
        //totalContacts.add(contact);
        // step 3: creating an opportunity
        Opportunity opportunity = null;
        boolean repeatOpportunity = true;
        while (repeatOpportunity) {
            try {
                System.out.println("Creating a new opportunity: ");
                opportunity = newOpportunity(getAnswer("Please enter product type: HYBRID, FLATBED or BOX"),
                        getNumber("Please enter the number of trucks considered for purchase: "),
                        contact, lead.getSalesRepId());
                repeatOpportunity = false;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
        //totalOpportunities.add(opportunity);
        opportunityRepository.save(opportunity);
        System.out.println("Created a new opportunity: ");
        System.out.println(opportunity);
        // step 4: Choose between new account or existing account
        Account account = null;
        boolean repeatAccQuestion = true;
        while (repeatAccQuestion) {
            try {
                account = accountQuestion(contact, opportunity, accountRepository);
                opportunity.setAccountId(accountRepository.findById(account.getId()).get());
                opportunityRepository.save(opportunity);
                repeatAccQuestion = false;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
        // step 6: adding the newly created objects to the lists and removing the lead
        //leadMap.remove(id);
        leadRepository.deleteById(id);
        System.out.println("Lead " + id + " removed fromm the database.");
    }

    public static Account accountQuestion(Contact contact, Opportunity opportunity, AccountRepository accountRepository) {
        String accQuestion = null;
        Account account = null;
        if (accountRepository.findAll().isEmpty()) {
            System.out.println("There are no existing accounts.");
            accQuestion = "y";
        } else {
            accQuestion = getAnswer("Would you like to create a new Account? (Y/N): ");
        }
        if (accQuestion.equals("y") || accQuestion.equals("Y")) {
            boolean repeatAcc = true;
            while (repeatAcc) {
                try {
                    account = newAccount(contact, opportunity);
                    repeatAcc = false;
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
            //totalAccounts.add(account);
            accountRepository.save(account);
            System.out.println("Created a new account: ");
            System.out.println(account);
        } else if (accQuestion.equals("n") || accQuestion.equals("N")) {
            boolean repeatAcc = true;
            while (repeatAcc) {
                try {
                    Long id = getNumber("Please enter the id of the account that you want to associate the opportunity with: ");
                    account = existingAccount(id, contact, opportunity, accountRepository);

                    repeatAcc = false;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid answer, only Y or N!");
        }
        return account;
    }

    public static Account newAccount(Contact contact, Opportunity opportunity) {
        Account account = null;
        boolean repeatAccount = true;
        while (repeatAccount) {
            try {
                System.out.println("Creating a new account: ");
                account = newAccount(getAnswer("Please enter industry type: PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER"),
                        getNumber("Please enter the number of employees in the company: "),
                        getAnswer("PLease enter the city in which the company is based: "),
                        getAnswer("PLease enter the country in which the company is based: "), contact, opportunity);
                repeatAccount = false;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        return account;
    }

    public static Account existingAccount(Long id, Contact contact, Opportunity opportunity, AccountRepository accountRepository) {
        if (!accountRepository.existsById(id) || accountRepository.findAll().isEmpty()) throw new IllegalArgumentException("No account found with this id!");
        Account account = accountRepository.findById(id).get();
        //account.setContacts(contact);
        account.getOpportunities().add(opportunity);
        accountRepository.save(account);
        return account;
    }

    public static Contact newContact(Lead lead) {
        Contact contact = new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
        System.out.println("Lead converted into a new contact: ");
        System.out.println(contact);
        return contact;
    }

    public static Opportunity newOpportunity(String product, long quantity, Contact contact, SalesRep salesRep) {
        Opportunity opportunity = new Opportunity(product, quantity, contact, salesRep);
        return opportunity;
    }

    public static Account newAccount(String industry, long employeeCount, String city, String country, Contact contact, Opportunity opportunity) {
        Account account = new Account(industry, employeeCount, city, country);
        account.setContacts(contact);
        account.getOpportunities().add(opportunity);
        return account;
    }

    public static SalesRep newSalesRep(String name) {
        SalesRep salesRep = new SalesRep(name);
        System.out.println("New SalesRep created: ");
        return salesRep;
    }

    public static void showLeads(LeadRepository leadRepository) {
        if (leadRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("There are no leads to show.");
        } else {
            System.out.println("\033[0;1m Existing leads: \033[0;0m\n");
            for (Lead lead : leadRepository.findAll()) {
                System.out.println("•" + lead);
            }
        }
    }

    public static void showSalesReps(SalesRepRepository salesRepRepository) {
        if (salesRepRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("There are no SalesReps to show.");
        } else {
            System.out.println("\033[0;1m Existing SalesReps: \033[0;0m\n");
            for (SalesRep salesRep : salesRepRepository.findAll()) {
                System.out.println("•" + salesRep);
            }
        }
    }

//    public static void showLeads(Map<Long, Lead> leadMap) {
//        if (leadMap.isEmpty()) {
//            throw new IllegalArgumentException("There are no leads to show.");
//        } else {
//            System.out.println("\033[0;1m Existing leads: \033[0;0m\n");
//            for (Lead lead : leadMap.values()) {
//                System.out.println("•" + lead.toString());
//            }
//        }
//    }

    public static void showOpportunities(OpportunityRepository opportunityRepository) {
        System.out.println("\033[0;1m Existing opportunities: \033[0;0m\n");
        for (Opportunity opportunity : opportunityRepository.findAll()) {
            System.out.println("•" + opportunity);
        }
    }


    public static boolean validatePhone(String phoneStr) {
        Matcher matcher = VALID_PHONENUMBER_REGEX.matcher(phoneStr);
        return matcher.find();
    }


    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}

