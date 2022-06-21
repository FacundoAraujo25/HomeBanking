package com.mindhub.HomeBanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private TransactionType type;
    private double amount,currentBalance;
    private String description;
    private LocalDateTime date;


    @ManyToOne(fetch = FetchType.EAGER)  // this is the inverse of an OneToMany relationship     // tells JPA to make sure that when an account is loaded from the database, the client/owner data should be loaded too
    @JoinColumn(name="account_id")    //For each row of the account database table, include a column client_id with the ID of the owner of the account.
    private Account account;

    public Transaction (){}

    public Transaction (TransactionType type,double amount,String description, LocalDateTime date, Account account, double currentBalance){

        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.account = account;
        this.currentBalance = currentBalance;

    }

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
