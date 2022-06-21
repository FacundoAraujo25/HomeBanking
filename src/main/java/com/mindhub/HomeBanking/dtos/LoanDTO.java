package com.mindhub.HomeBanking.dtos;

import com.mindhub.HomeBanking.models.Loan;

import java.util.List;

public class LoanDTO {


    private long id;
    private String name;
    private int maxAmount;
    private float pctInterest;
    private List<Integer>Payments;


    public LoanDTO () {}

    public LoanDTO (Loan loan){
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.Payments = loan.getPayments();
        this.pctInterest = loan.getPctInterest();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return Payments;
    }

    public void setPayments(List<Integer> payments) {
        Payments = payments;
    }
}
