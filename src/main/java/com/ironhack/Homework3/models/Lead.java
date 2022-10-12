package com.ironhack.Homework3.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "lead_table")
public class Lead {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long phoneNumber;
    private String email;
    private String companyName;
    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRepId;

    public Lead() {
    }

    public Lead(String name, long phoneNumber, String email, String companyName, SalesRep salesRepId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRepId = salesRepId;
    }

    public Lead(String name, long phoneNumber, String email, String companyName) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public SalesRep getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(SalesRep salesRepId) {
        this.salesRepId = salesRepId;
    }

    @Override
    public String toString() {
        return "Lead: id = " + getId() + ", name = " + name + ", phoneNumber = " + phoneNumber +
                ", email = " + email + ", companyName = " + companyName + "\n";
    }
}
