package com.ironhack.Homework3.models;

import com.ironhack.Homework3.enums.Product;
import com.ironhack.Homework3.enums.Status;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Opportunity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Product product;
    private long quantity;
    @Embedded
    private Contact decisionMaker;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;
    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRepId;

    public Opportunity() {
        setStatus(Status.OPEN);
    }

//    public Opportunity(String product, long quantity, Contact decisionMaker) {
//        setProduct(product);
//        setQuantity(quantity);
//        setStatus(Status.OPEN);
//        setDecisionMaker(decisionMaker)
//        ;
//    }


    // Constructor sin account
    public Opportunity(String product, long quantity, Contact decisionMaker, SalesRep salesRepId) {
        setProduct(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        this.status = Status.OPEN;
        setSalesRepId(salesRepId);
    }

    // Constructor normal
    public Opportunity(String product, long quantity, Contact decisionMaker, Account accountId, SalesRep salesRepId) {
        setProduct(product);
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        this.status = Status.OPEN;
        setAccountId(accountId);
        setSalesRepId(salesRepId);
    }

    // Constructor para tests! No borrar
    public Opportunity(long quantity, Product product, Contact decisionMaker, Account accountId, SalesRep salesRepId) {
        this.product = product;
        setQuantity(quantity);
        setDecisionMaker(decisionMaker);
        this.status = Status.OPEN;
        setAccountId(accountId);
        setSalesRepId(salesRepId);
    }

    public Long getId() {
        return id;
    }


    public long getQuantity() {

        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(String product) {
        product = product.toUpperCase();
        if (product.equals("HYBRID") || product.equals("FLATBED") || product.equals("BOX")) {
            this.product = Product.valueOf(product);
        } else {
            throw new IllegalArgumentException("No such product type found. Please enter HYBRID, FLATBED or BOX");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public SalesRep getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(SalesRep salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Opportunity: id = " + id + ", product = " + product + ", trucks quantity = " + quantity + ", status = "
                + status + ", SalesRep name = " + salesRepId.getName() +
                "\n Decision maker " + decisionMaker;
    }


}
