package com.ironhack.Homework3.models;

import com.ironhack.Homework3.enums.Product;
import com.ironhack.Homework3.enums.Status;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long quantity;
    private Product product;
    private Status status;
    @Embedded
    private Contact decisionMaker;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;
    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRepId;

    public Opportunity(long quantity, Product product, Contact decisionMaker, Account accountId, SalesRep salesRepId) {
        this.quantity = quantity;
        this.product = product;
        this.status = Status.OPEN;
        this.decisionMaker = decisionMaker;
        this.accountId = accountId;
        this.salesRepId = salesRepId;
    }

    public Opportunity() {
        setStatus(Status.OPEN);
    }

    public Opportunity(String product, long quantity, Contact decisionMaker) {
        setProduct(product);
        setQuantity(quantity);
        setStatus(Status.OPEN);
        setDecisionMaker(decisionMaker);
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
        return "Opportunity: id = " + id + ", product = " + product + ", trucks quantity = " + quantity + ", status = " + status +
                "\n Decision maker " + decisionMaker;
    }
}
