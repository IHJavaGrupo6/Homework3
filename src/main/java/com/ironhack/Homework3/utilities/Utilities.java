package com.ironhack.Homework3.utilities;

import com.ironhack.Homework3.models.Account;
import com.ironhack.Homework3.models.Contact;
import com.ironhack.Homework3.models.Lead;
import com.ironhack.Homework3.models.Opportunity;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ironhack.Homework3.utilities.Menu.mainMenu;
import static java.lang.Long.parseLong;

public class Utilities {
    private static Map<Long, Lead> leadMap = new HashMap<>();
    private static List<Contact> totalContacts = new ArrayList<>();
    private static List<Opportunity> totalOpportunities = new ArrayList<>();
    private static List<Account> totalAccounts = new ArrayList<>();
    public static final Pattern VALID_PHONENUMBER_REGEX =
            Pattern.compile("\\A[0-9]{3}[0-9]{3}[0-9]{3}\\z", Pattern.CASE_INSENSITIVE);
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

    public static Lead newLead(String name, long phoneNumber, String email, String company) {
        System.out.println("Creating a new lead: ");
        if (!validatePhone(String.valueOf(phoneNumber)))
            throw new IllegalArgumentException("Invalid phone format");
        if (!validate(email)) throw new IllegalArgumentException("Invalid email format");
        Lead lead = new Lead(name, phoneNumber, email, company);
        System.out.println("New lead created: ");
        System.out.println(lead);
        return lead;
    }
    public static void convertLead(int id) {
        // step 1: fetching the lead
        if (id < 0 || id >= leadMap.size()) throw new IllegalArgumentException("No lead found with this id!");
        // step 2: creating a contact
        Contact contact;
        contact = newContact(leadMap.get(id));
        // step 3: creating an opportunity
        Opportunity opportunity = null;
        boolean repeatOpportunity = true;
        while (repeatOpportunity) {
            try {
                System.out.println("Creating a new opportunity: ");
                opportunity = newOpportunity(getAnswer("Please enter product type: HYBRID, FLATBED or BOX"),
                        getNumber("Please enter the number of trucks considered for purchase: "), contact);
                repeatOpportunity = false;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
        // step 4: Choose between new account or existing account
        boolean repeatAccQuestion = true;
        Account account= null;
        while (repeatAccQuestion) {
            try {
                String accQuestion = getAnswer("Would you like to create a new Account?(Y/N): ");
                if (accQuestion.equals("Y")) {
                    account = createNewAccount(contact, opportunity);
                    repeatOpportunity = false;
                } else if (accQuestion.equals("N")) {
                    account = null;
                    repeatOpportunity = false;
                }else throw new  IllegalArgumentException("Invalid answer, only Y/N");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
        // step 6: adding the newly created objects to the lists and removing the lead
        totalContacts.add(contact);
        totalOpportunities.add(opportunity);
        totalAccounts.add(account);
        leadMap.remove(id);
    }

    public static Account createNewAccount(Contact contact,Opportunity opportunity){
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
                System.err.println(e.getMessage());
            }
        }
        return account;
    }

    public static Contact newContact(Lead lead) {
        Contact contact = new Contact(lead.getName(), lead.getPhoneNumber(), lead.getEmail(), lead.getCompanyName());
        System.out.println("Lead converted into a new contact: ");
        System.out.println(contact);
        return contact;
    }

    public static Opportunity newOpportunity(String product, long quantity, Contact contact) {
        Opportunity opportunity = new Opportunity(product, quantity, contact);
        System.out.println("Created a new opportunity: ");
        System.out.println(opportunity);
        return opportunity;
    }

    public static Account newAccount(String industry, long employeeCount, String city, String country, Contact contact, Opportunity opportunity) {
        Account account = new Account(industry, employeeCount, city, country, contact, opportunity);
        System.out.println("Created a new account: ");
        System.out.println(account);
        return account;
    }

    public static void showLeads(Map<Long, Lead> leadMap) {
        if (leadMap.isEmpty()) {
            throw new IllegalArgumentException("There are no leads to show.");
        } else {
            System.out.println("\033[0;1m Existing leads: \033[0;0m\n");
            for (Lead lead : leadMap.values()) {
                System.out.println("•" + lead.toString());
            }
        }
    }

    public static void showOpportunities() {
        System.out.println("\033[0;1m Existing opportunities: \033[0;0m\n");
        for (Opportunity opportunity : totalOpportunities) {
            System.out.println("•" + opportunity.toString());
        }
    }
    public static void backToMainMenu(Exception e) {
        System.err.println(e.getMessage());
        System.err.println("Going back to the main menu.");
        mainMenu();
    }

    public static Map<Long, Lead> getLeadMap() {
        return leadMap;
    }

    public static void setLeadMap(Map<Long, Lead> leadMap) {
        Utilities.leadMap = leadMap;
    }

    public static List<Contact> getTotalContacts() {
        return totalContacts;
    }

    public static void setTotalContacts(List<Contact> totalContacts) {
        Utilities.totalContacts = totalContacts;
    }

    public static List<Opportunity> getTotalOpportunities() {
        return totalOpportunities;
    }

    public static void setTotalOpportunities(List<Opportunity> totalOpportunities) {
        Utilities.totalOpportunities = totalOpportunities;
    }

    public static List<Account> getTotalAccounts() {
        return totalAccounts;
    }

    public static void setTotalAccounts(List<Account> totalAccounts) {
        Utilities.totalAccounts = totalAccounts;
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

