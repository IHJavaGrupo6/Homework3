package com.ironhack.Homework3.utilities;

import com.ironhack.Homework3.enums.Status;
import com.ironhack.Homework3.models.*;
import com.ironhack.Homework3.repositories.AccountRepository;
import com.ironhack.Homework3.repositories.LeadRepository;
import com.ironhack.Homework3.repositories.OpportunityRepository;
import com.ironhack.Homework3.repositories.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static com.ironhack.Homework3.utilities.Utilities.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


public class Menu {

    private static Scanner input;


    public static void mainMenu(AccountRepository accountRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, SalesRepRepository salesRepRepository) {
        System.out.println("=========");
        System.out.println("\033[0;1mMAIN MENU\033[0;0m");
        System.out.println("\033[0;1mAvailable commands: \033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mNew SalesRep \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mShow SalesReps \u001B[0m\033[0;0m to show the list of existing SalesReps");
        System.out.println("\u001B[36m    Existing SalesReps: " + salesRepRepository.findAll().size() + "\u001B[0m");
        System.out.println("\033[0;1m• \u001B[34mNew lead \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mShow leads \u001B[0m\033[0;0m to show the list of existing leads");
        System.out.println("\u001B[36m    Existing leads: " + leadRepository.findAll().size() + "\u001B[0m");
        System.out.println("\033[0;1m• \u001B[34mLook up lead + id \u001B[0m\033[0;0m to find a lead by its id number and display its info");
        System.out.println("\033[0;1m• \u001B[34mConvert + id \u001B[0m\033[0;0m to find a lead by its id number and convert it into a new opportunity");
        System.out.println("\033[0;1m• \u001B[34mShow opportunities \u001B[0m\033[0;0m to show the list of existing opportunities (both open and closed)");
        System.out.println("\u001B[36m    Existing opportunities: " + opportunityRepository.findAll().size() + "\u001B[0m");
        System.out.println("\033[0;1m• \u001B[34mLook up opportunity + id \u001B[0m\033[0;0m to find a lead by its id number and display its info");
        System.out.println("\033[0;1m• \u001B[34mClose-Won + id \u001B[0m\033[0;0m to close an oportunity that ended with a sale ");
        System.out.println("\033[0;1m• \u001B[34mClose-Lost + id \u001B[0m\033[0;0m to close a lost oportunity");
        System.out.println("\033[0;1m• \u001B[34mReport \u001B[0m\033[0;0m to access report features");
        System.out.println("\033[0;1m• \u001B[34mExit \u001B[0m\033[0;0m");
        System.out.println("What do you want to do? ");
        try {
            getMethodInput(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
        } catch (IllegalArgumentException e) {
            backToMainMenu(e, accountRepository, leadRepository, opportunityRepository, salesRepRepository);
        }
    }

    public static void getMethodInput(AccountRepository accountRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, SalesRepRepository salesRepRepository) {
        input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        long id = 0;
        try {
            id = parseLong(methodAndId.replaceAll("\\D+", ""));
        } catch (NumberFormatException ignored) {

        }
        switch (method) {
            case "newsalesrep":
                SalesRep salesRep = null;
                boolean repeatSalesRep = true;
                System.out.println("Creating a new SalesRep: ");
                while (repeatSalesRep) {
                    try {
                        String name = getAnswer("Please enter the name of the new SalesRep: ");
                        salesRep = newSalesRep(name);
                        repeatSalesRep = false;
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        System.err.println("Going back to new SalesRep creation");
                    }
                }
                //Utilities.getTotalSalesReps().add(salesRep);
                salesRepRepository.save(salesRep);
                System.out.println(salesRep);
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "showsalesreps":
                try {
                    showSalesReps(salesRepRepository);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "newlead":
                Lead lead = null;
                boolean repeatLead = true;
                if (salesRepRepository.findAll().isEmpty()) {
                    System.err.println("Create a SalesRep first!");
                    mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
                }
                System.out.println("Creating a new lead: ");
                while (repeatLead) {
                    try {
                        String name = getAnswer("Please enter the name of the new lead: ");
                        Long number = getNumber("Please enter a phone number for the new lead: ");
                        String mail = getAnswer("Please enter an email for the new lead: ");
                        String company = getAnswer("Please enter the name of the company for the new lead: ");
                        Long salesRepId = getNumber("Please enter the id of the associated SalesRep: ");
                        lead = newLead(name, number, mail, company, salesRepId, salesRepRepository);
                        repeatLead = false;
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        System.err.println("Going back to new lead creation");
                    }
                }
                //Utilities.getLeadMap().put(lead.getId(), lead);
                leadRepository.save(lead);
                System.out.println(lead);
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "showleads":
                try {
                    showLeads(leadRepository);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "lookuplead":
                try {
                    System.out.println(leadRepository.findById(id).get());
                } catch (RuntimeException e) {
                    backToMainMenu(e, accountRepository, leadRepository, opportunityRepository, salesRepRepository);
                }
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "convert":
                boolean repeatConvertLead = true;
                while (repeatConvertLead) {
                    try {
                        convertLead(id, leadRepository, opportunityRepository, accountRepository);
                        repeatConvertLead = false;
                    } catch (IllegalArgumentException e) {
                        backToMainMenu(e, accountRepository, leadRepository, opportunityRepository, salesRepRepository);
                    }
                }
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "showopportunities":
                showOpportunities(opportunityRepository);
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "lookupopportunity":
                try {
                    System.out.println(opportunityRepository.findById(id).get());
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e, accountRepository, leadRepository, opportunityRepository, salesRepRepository);
                }
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "closewon":
                try {
                    Opportunity opportunity = opportunityRepository.findById(id).get();
                    opportunity.setStatus(Status.CLOSED_WON);
                    opportunityRepository.save(opportunity);
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e, accountRepository, leadRepository, opportunityRepository, salesRepRepository);
                }
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "closelost":
                try {
                    Opportunity opportunity = opportunityRepository.findById(id).get();
                    opportunity.setStatus(Status.CLOSED_LOST);
                    opportunityRepository.save(opportunity);
                    System.out.println(opportunityRepository.findById(id).get());
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e, accountRepository, leadRepository, opportunityRepository, salesRepRepository);
                }
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);

            case "report":
                try {
                    ReportFeatures.repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e, accountRepository, leadRepository, opportunityRepository, salesRepRepository);
                }
                mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
            case "exit":
                System.out.println("Good bye!");
                System.exit(0);
            default:
                throw new IllegalArgumentException("No such command found. Please enter a valid command!");
        }
    }

    public static void backToMainMenu(Exception e, AccountRepository accountRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, SalesRepRepository salesRepRepository) {
        System.err.println(e.getMessage());
        System.err.println("Going back to the main menu.");
        mainMenu(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
    }
}
