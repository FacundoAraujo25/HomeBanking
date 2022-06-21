package com.mindhub.HomeBanking.dtos;

import com.mindhub.HomeBanking.models.Client;
import com.mindhub.HomeBanking.models.ClientLoan;

public class ClientLoanDTO {

    private long idClientLoan;
    private long idLoan;

    private String name;
    private int amount,payments;



    public ClientLoanDTO(){}

    public ClientLoanDTO(ClientLoan clientLoan){
        this.idClientLoan = clientLoan.getId();
        this.idLoan = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }

    public long getIdClientLoan() {
        return idClientLoan;
    }

    public long getIdLoan() {
        return idLoan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }
}
