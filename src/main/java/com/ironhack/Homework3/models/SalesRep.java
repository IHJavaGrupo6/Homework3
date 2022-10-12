package com.ironhack.Homework3.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public SalesRep(String name, List<Lead> leads, List<Opportunity> opportunities) {
        this.name = name;
        this.leads = leads;
        this.opportunities = opportunities;
    }

    @Override
    public String toString() {
        return "SalesRep: id = " + getId() + ", name = " + name +
                "\n Leads List \n" + leads + "\n Opportunity List \n" + opportunities;
    }
}
