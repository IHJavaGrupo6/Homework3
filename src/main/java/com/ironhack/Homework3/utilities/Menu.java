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


public class Menu {
    @Autowired

    AccountRepository accountRepository;
    @Autowired

    LeadRepository leadRepository;
    @Autowired

    OpportunityRepository opportunityRepository;
    @Autowired

    SalesRepRepository salesRepRepository;

    private  Scanner input;


    public  void mainMenu() {
        System.out.println("=========");
        System.out.println("\033[0;1mMAIN MENU\033[0;0m");
        System.out.println("\033[0;1mAvailable commands: \033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mNew lead \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mShow leads \u001B[0m\033[0;0m to show the list of existing leads");
        System.out.println("\u001B[36m    Existing leads: " + Utilities.getLeadMap().keySet().size() + "\u001B[0m");
        System.out.println("\033[0;1m• \u001B[34mLook up lead + id \u001B[0m\033[0;0m to find a lead by its id number and display its info");
        System.out.println("\033[0;1m• \u001B[34mConvert + id \u001B[0m\033[0;0m to find a lead by its id number and convert it into a new opportunity");
        System.out.println("\033[0;1m• \u001B[34mShow opportunities \u001B[0m\033[0;0m to show the list of existing opportunities (both open and closed)");
        System.out.println("\u001B[36m    Existing opportunities: " + Utilities.getTotalOpportunities().size() + "\u001B[0m");
        System.out.println("\033[0;1m• \u001B[34mLook up opportunity + id \u001B[0m\033[0;0m to find a lead by its id number and display its info");
        System.out.println("\033[0;1m• \u001B[34mClose-Won + id \u001B[0m\033[0;0m to close an oportunity that ended with a sale ");
        System.out.println("\033[0;1m• \u001B[34mClose-Lost + id \u001B[0m\033[0;0m to close a lost oportunity");
        System.out.println("\033[0;1m• \u001B[34mNew Sales Rep \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mCreate Reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mExit \u001B[0m\033[0;0m");
        System.out.println("What do you want to do? ");
        try {
            getMethodInput();
        } catch (IllegalArgumentException e) {
            backToMainMenu(e);
        }
    }

    public  void getMethodInput() {
        input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        int id = 0;
        try {
            id = parseInt(methodAndId.replaceAll("\\D+", ""));
        } catch (NumberFormatException ignored) {

        }
        switch (method) {
            case "newlead":
                Lead lead = null;
                boolean repeatLead = true;
                while (repeatLead) {
                    try {
                        String name = getAnswer("Please enter the name of the new lead: ");
                        Long number = getNumber("Please enter a phone number for the new lead: ");
                        if (!Utilities.validatePhone(String.valueOf(number)))
                            throw new IllegalArgumentException("Invalid phone format");
                        String mail = getAnswer("Please enter an email for the new lead: ");
                        if (!Utilities.validate(mail)) throw new IllegalArgumentException("Invalid email format");
                        String company = getAnswer("Please enter the name of the company for the new lead: ");
                        Long salesRepId = getNumber("Please enter the id of the associated SalesRep: ");
                        if(!salesRepRepository.existsById(salesRepId)) System.out.println("NO ESTA");
                        lead = newLead(name, number, mail, company,salesRepRepository.getReferenceById(salesRepId));
                        repeatLead = false;
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                        System.err.println("Going back to new lead creation");
                    }
                }
                Utilities.getLeadMap().put(lead.getId(), lead);
                leadRepository.save(lead);

                mainMenu();
            case "showleads":
                try {
                    showLeads(getLeadMap());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                mainMenu();
            case "lookuplead":
                try {
                    System.out.println(Utilities.getLeadMap().get(id).toString());
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }

                mainMenu();
            case "convert":
                boolean repeatConvertLead = true;
                while (repeatConvertLead) {
                    try {
                        convertLead(id);
                        repeatConvertLead = false;
                    } catch (IllegalArgumentException e) {
                        backToMainMenu(e);
                    }
                }
                mainMenu();
            case "showopportunities":
                showOpportunities();
                mainMenu();
            case "lookupopportunity":
                try {
                    System.out.println(Utilities.getTotalOpportunities().get(id).toString());
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "closewon":
                try {
                    Utilities.getTotalOpportunities().get(id).setStatus(Status.CLOSED_WON);
                    System.out.println(Utilities.getTotalOpportunities().get(id));
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "closelost":
                try {
                    Utilities.getTotalOpportunities().get(id).setStatus(Status.CLOSED_LOST);
                    System.out.println(Utilities.getTotalOpportunities().get(id));
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "newsalesrep":
                SalesRep salesRep = null;
                boolean repeatSalesRep = true;
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
                Utilities.getTotalSalesReps().add(salesRep);

                mainMenu();

            case "createreports":
                try {
                    ReportFeatures.repMenu();
                } catch (IllegalArgumentException e) {
                    backToMainMenu(e);
                }
                mainMenu();
            case "exit":
                System.out.println("Good bye!");
                System.exit(0);
            default:
                throw new IllegalArgumentException("No such command found. Please enter a valid command!");
        }
    }
    public  void backToMainMenu(Exception e) {
        System.err.println(e.getMessage());
        System.err.println("Going back to the main menu.");
        mainMenu();
    }
}
