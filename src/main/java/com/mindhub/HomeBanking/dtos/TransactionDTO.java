package com.mindhub.HomeBanking.dtos;

import com.mindhub.HomeBanking.models.Account;
import com.mindhub.HomeBanking.models.Transaction;
import com.mindhub.HomeBanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {

    private long id;

    private TransactionType type;
    double amount,currentBalance;
    String description;
    LocalDateTime date;
    Account account;

    public TransactionDTO (){}

    public TransactionDTO (Transaction transaction){

        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.id = transaction.getId();
        this.currentBalance = transaction.getCurrentBalance();

    }

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    /*public void setType(TransactionType type) {
        this.type = type;
    }*/

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

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
