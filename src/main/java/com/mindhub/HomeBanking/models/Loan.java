package com.mindhub.HomeBanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String name;
    private int maxAmount;
    private float pctInterest;
    //List<Integer> Payments = List.of(6, 12, 24, 48);

    @ElementCollection   //is used to create lists of embeddable objects. An embeddable object is data intended for use only in the object containing it.
    @Column(name="Payments")
    private List<Integer> Payments = List.of(6, 12, 24, 48);
            //new ArrayList<>();

    @OneToMany(mappedBy="loan", fetch=FetchType.EAGER)
    Set<ClientLoan> clientLoans = new HashSet<>();

    public Set<ClientLoan> getLoans() {
        return clientLoans;
    }

    public void addClientLoan(ClientLoan clientLoan) {
        clientLoan.setLoan(this);
        clientLoans.add(clientLoan);
    }

    public List<Client> getClients() {
        return clientLoans.stream().map(loan->loan.getClient()).collect(toList());
    }


    public Loan () {}

    public Loan (String name, int maxAmount, List<Integer> Payments,float pctInterest){
        this.name = name;
        this.maxAmount = maxAmount;
        this.Payments = Payments;
        this.pctInterest = pctInterest;
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
        this.Payments = payments;
    }

    public float getPctInterest() {
        return pctInterest;
    }

    public void setPctInterest(float pctInterest) {
        this.pctInterest = pctInterest;
    }
}
