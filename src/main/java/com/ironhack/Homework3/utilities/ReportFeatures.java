package com.ironhack.Homework3.utilities;


import com.ironhack.Homework3.repositories.AccountRepository;
import com.ironhack.Homework3.repositories.LeadRepository;
import com.ironhack.Homework3.repositories.OpportunityRepository;
import com.ironhack.Homework3.repositories.SalesRepRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ReportFeatures {
    private static List<Object[]> list = new ArrayList<>();

    public static void repMenu(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                               LeadRepository leadRepository, AccountRepository accountRepository) {
        System.out.println("=========");
        System.out.println("\033[0;1mREPORT MENU\033[0;0m");
        System.out.println("\033[0;1mAvailable commands: \033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mSalesRep reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mProduct reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mCountry reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mCity reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mIndustry reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mEmployee Count reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mQuantity reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mOpportunity reports \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack to main menu \u001B[0m\033[0;0m");
        System.out.println("What do you want to do? ");
        try {
            getMethodInputReports(accountRepository, leadRepository, opportunityRepository, salesRepRepository);
        } catch (IllegalArgumentException e) {
            repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
        }
    }

    public static void getMethodInputReports(AccountRepository accountRepository, LeadRepository leadRepository, OpportunityRepository opportunityRepository, SalesRepRepository salesRepRepository) {
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method) {
            case "salesrep":
                salesReports(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "product":
                productReport(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "country":
                countryReport(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "city":
                cityReport(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "industry":
                industryReport(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "employeecount":
                employeeCountReport(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "quantity":
                quantityReport(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "opportunity":
                opportunityReport(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
        }
    }

    public static void salesReports(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                             LeadRepository leadRepository, AccountRepository accountRepository){
        System.out.println("\033[0;1m• \u001B[34mReport Lead by SalesRep \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Opportunity by SalesRep \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Won by SalesRep \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Lost by SalesRep \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Open by SalesRep \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack \u001B[0m\033[0;0m");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method){
            case "reportleadbysalesrep":
                list = salesRepRepository.countLeadsBySalesRep();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportopportunitybysalesrep":
                list = salesRepRepository.countOpportunitiesBySalesRep();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosedwonbysalesrep":
                list = salesRepRepository.findCountByStatusClosedWon();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosedlostbysalesrep":
                list = salesRepRepository.findCountByStatusClosedLost();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportopenbysalesrep":
                list = salesRepRepository.findCountByStatusOpen();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);

        }
    }

    public static void productReport(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                              LeadRepository leadRepository, AccountRepository accountRepository){
        System.out.println("\033[0;1m• \u001B[34mReport Opportunity By The Product \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Won By The Product \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Lost By The Product \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Open By The Product \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack \u001B[0m\033[0;0m");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method){
            case "reportopportunitybytheproduct":
                list = opportunityRepository.countOpportunitiesByProduct();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosewonbytheproduct":
                list = opportunityRepository.countOpportunitiesByProductWhereStatusLikeWon();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosedlostbytheproduct":
                list = opportunityRepository.countOpportunitiesByProductWhereStatusLikeLost();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportopenbytheproduct":
                list = opportunityRepository.countOpportunitiesByProductWhereStatusLikeOpen();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);

        }
    }

    public static void countryReport(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                              LeadRepository leadRepository, AccountRepository accountRepository){
        System.out.println("\033[0;1m• \u001B[34mReport Opportunity By Country \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Won By Country \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Lost By Country \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Open By Country \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack \u001B[0m\033[0;0m");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method){
            case "reportopportunitybycountry":
                list = opportunityRepository.countOpportunitiesByCountry();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosewonbycountry":
                list = opportunityRepository.countOpportunitiesByCountryWhereStatusLikeWon();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosedlostbycountry":
                list = opportunityRepository.countOpportunitiesByCountryWhereStatusLikeLost();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportopenbycountry":
                list = opportunityRepository.countOpportunitiesByCountryWhereStatusLikeOpen();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);

        }
    }

    public static void cityReport(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                           LeadRepository leadRepository, AccountRepository accountRepository){
        System.out.println("\033[0;1m• \u001B[34mReport Opportunity By City \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Won By City \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Lost By City \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Open By City \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack \u001B[0m\033[0;0m");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method){
            case "reportopportunitybycity":
                list = opportunityRepository.countOpportunitiesByCity();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosewonbycity":
                list = opportunityRepository.countClosedWonOpportunitiesByCity();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosedlostbycity":
                list = opportunityRepository.countClosedLostOpportunitiesByCity();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportopenbycity":
                list = opportunityRepository.countOpenOpportunitiesByCity();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);

        }
    }

    public static void industryReport(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                               LeadRepository leadRepository, AccountRepository accountRepository){
        System.out.println("\033[0;1m• \u001B[34mReport Opportunity By Industry \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Won By Industry \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Close-Lost By Industry \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mReport Open By Industry \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack \u001B[0m\033[0;0m");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method){
            case "reportopportunitybyindustry":
                list = accountRepository.countOpportunitiesByIndustry();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosewonbyindustry":
                list = accountRepository.countClosedWonOpportunitiesByIndustry();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportclosedlostbyindustry":
                list = accountRepository.countClosedLostOpportunitiesByIndustry();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "reportopenbyindustry":
                list = accountRepository.countOpenOpportunitiesByIndustry();
                for (Object[] objects : list) {
                    System.out.println(objects[0] + " " + objects[1]);
                }
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
        }
    }

    public static void employeeCountReport(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                                    LeadRepository leadRepository, AccountRepository accountRepository){
        System.out.println("\033[0;1m• \u001B[34mMean EmployeeCount \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMeadian EmployeeCount \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMax EmployeeCount \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMin EmployeeCount \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack \u001B[0m\033[0;0m");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method){
            case "meanemployeecount":
                BigDecimal mean = accountRepository.meanEmployeeCount();
                System.out.println("Mean= "+ mean);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "medianemployeecount":
                Long median = accountRepository.medianEmployeeCount();
                System.out.println("Median= "+ median);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "maxemployeecount":
                Long max = accountRepository.maxEmployeeCount();
                System.out.println("Max= "+ max);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "minemployeecount":
                Long min = accountRepository.minEmployeeCount();
                System.out.println("Min= "+ min);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);

        }
    }
    public static void quantityReport(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                               LeadRepository leadRepository, AccountRepository accountRepository){
        System.out.println("\033[0;1m• \u001B[34mMean Quantity \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMeadian Quantity \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMax Quantity \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMin Quantity \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack \u001B[0m\033[0;0m");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method){
            case "meanquantity":
                Double mean = opportunityRepository.averageQuantityOfProducts();
                System.out.println("Mean= "+ mean);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "medianquantity":
                Long median = null;
                System.out.println("Median= "+ median);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "maxquantity":
                Long max = opportunityRepository.maxQuantityOfProducts();
                System.out.println("Max= "+ max);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "minquantity":
                Long min = opportunityRepository.minQuantityOfProducts();
                System.out.println("Min= "+ min);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
        }
    }

    public static void opportunityReport(SalesRepRepository salesRepRepository, OpportunityRepository opportunityRepository,
                                  LeadRepository leadRepository, AccountRepository accountRepository){
        System.out.println("\033[0;1m• \u001B[34mMean Opps per Account \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMeadian Opps per Account \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMax Opps per Account \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mMin Opps per Account \u001B[0m\033[0;0m");
        System.out.println("\033[0;1m• \u001B[34mBack \u001B[0m\033[0;0m");
        System.out.println("");
        Scanner input = new Scanner(System.in);
        String methodAndId = input.nextLine().toLowerCase().replaceAll("\\W+", "");
        if (methodAndId.isBlank()) {
            throw new IllegalArgumentException("Nothing received. Please enter at valid command!");
        }
        String method = methodAndId.replaceAll("\\d+", "");
        switch (method){
            case "meanoppsperaccount":
                Double mean = opportunityRepository.meanOpportunitiesAccount();
                System.out.println("Mean= "+ mean);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "medianoppsperaccount":
                Long median = null;
                System.out.println("Median= "+ median);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "maxoppsperaccount":
                Long max = opportunityRepository.maxOpportunitiesAccount();
                System.out.println("Max= "+ max);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "minoppsperaccount":
                Long min = opportunityRepository.minOpportunitiesAccount();
                System.out.println("Min= "+ min);
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);
            case "back":
                repMenu(salesRepRepository, opportunityRepository, leadRepository, accountRepository);

        }
    }

}
