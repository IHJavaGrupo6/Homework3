package com.ironhack.Homework3.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "salesRepId")
    private List<Lead> leads;
    @OneToMany(mappedBy = "salesRepId")
    private List<Opportunity> opportunities;

    public SalesRep() {
    }

    public SalesRep(String name, List<Lead> leads, List<Opportunity> opportunities) {
        this.name = name;
        this.leads = leads;
        this.opportunities = opportunities;
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

    public List<Lead> getLeads() {
        return leads;
    }

    public void setLeads(List<Lead> leads) {
        this.leads = leads;
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }

    @Override
    public String toString() {
        return "SalesRep: id = " + this.id + ", name = " + name +
                "\n Leads List \n" + leads + "\n Opportunity List \n" + opportunities;
    }
}
