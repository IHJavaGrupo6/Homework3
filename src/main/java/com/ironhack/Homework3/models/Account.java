package com.ironhack.Homework3.models;

import com.ironhack.Homework3.enums.Industry;
import com.sun.istack.NotNull;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Account {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Industry industry;
    private long employeeCount;
    private String city;
    private String country;
    @ElementCollection
    private List<Contact> contacts;
    @OneToMany(mappedBy = "accountId")
    private List<Opportunity> opportunities;

    public Account() {
    }

    //  Constructor with empty contact list and opportunity list
    public Account(String industry, long employeeCount, String city, String country) {
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        contacts = new ArrayList<>();
        opportunities = new ArrayList<>();
    }

    //  Constructor with adding a contact and an opportunity to the lists
    public Account(String industry, long employeeCount, String city, String country, Contact contact, Opportunity opportunity) {
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        contacts.add(contact);
        opportunities.add(opportunity);
    }

    // Constructor using a list of contacts and a list of opportunities
    public Account(Industry industry, long employeeCount, String city, String country, List<Contact> contacts, List<Opportunity> opportunities) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contacts = contacts;
        this.opportunities = opportunities;
    }

    //  Getters
    public Long getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<Contact> getContacts() {

        return contacts;
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    //  Setters
    public void setIndustry(String industry) {
        industry = industry.toUpperCase();
        if (industry.equals("PRODUCE")
                || industry.equals("ECOMMERCE")
                || industry.equals("MANUFACTURING")
                || industry.equals("MEDICAL")
                || industry.equals("OTHER")) {
            this.industry = Industry.valueOf(industry);
        } else {
            throw new IllegalArgumentException("No such industry type found. Please enter PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL or OTHER");
        }
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static final Pattern VALID_PHONENUMBER_REGEX =
            Pattern.compile("\\A[0-9]{3}[0-9]{3}[0-9]{3}\\z", Pattern.CASE_INSENSITIVE);

    public static boolean validatePhone(String phoneStr) {
        Matcher matcher = VALID_PHONENUMBER_REGEX.matcher(phoneStr);
        return matcher.find();
    }


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public String toString() {
        return "Account: id = " + id + ", industry= " + industry + ", employeeCount= " + employeeCount + ", city= " + city + ", country= " + country +
                "\n Contact List \n" + contacts + "\n Opportunity List \n" + opportunities;
    }

    public void setOpportunities(List<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }
}
