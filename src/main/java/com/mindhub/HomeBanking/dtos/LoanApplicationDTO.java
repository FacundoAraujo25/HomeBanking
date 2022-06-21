package com.mindhub.HomeBanking.dtos;

import com.mindhub.HomeBanking.models.ClientLoan;
import com.mindhub.HomeBanking.models.Loan;

public class LoanApplicationDTO {


    private long idLoan;
    private int amount,payments;
    private String accountNumber;
    private float pctInterest;



    public LoanApplicationDTO(){}

    public LoanApplicationDTO(Loan loan, int amount, int payments, String accountNumber, float pctInterest )
    {
        this.idLoan = idLoan;
        this.amount = amount;
        this.payments = payments;
        this.accountNumber = accountNumber;
        this.pctInterest = pctInterest;
    }


    public long getIdLoan() {
        return idLoan;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getPctInterest() {
        return pctInterest;
    }

    public void setPctInterest(float pctInterest) {
        this.pctInterest = pctInterest;
    }
}
