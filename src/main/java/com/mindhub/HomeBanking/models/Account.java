package com.mindhub.HomeBanking.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String number;
    private double balance;
    private LocalDateTime creationDate;
    private boolean disable;
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER)  // this is the inverse of an OneToMany relationship     // tells JPA to make sure that when an account is loaded from the database, the client/owner data should be loaded too
    @JoinColumn(name="client_id")    //For each row of the account database table, include a column client_id with the ID of the owner of the account.
    private Client client;
    //Given an account, you can get the client by retrieving the person with the stored client_id.
    //Given an account, you can get all that accounts by collecting all the rows of the account table that have that client's ID in the client_id column.

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    Set<Transaction> transactions = new HashSet<>();

    public Set<Transaction> getTransactions(){return transactions;}

    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        transactions.add(transaction);
    }

    public Account () {}

    public Account (String number, double balance, LocalDateTime creationDate,Client client,boolean disable,AccountType accountType){
        this.number = number;
        this.balance = balance;
        this.creationDate = creationDate;
        this.client = client;
        this.disable = disable;
        this.accountType = accountType;
    }

    public long getId() {
        return id;
    }


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
