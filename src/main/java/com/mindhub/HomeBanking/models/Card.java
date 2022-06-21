package com.mindhub.HomeBanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String cardholder;
    private LocalDate fromDate,thruDate;
    private String number;
    private int cvv;
    private CardColor cardColor;
    private CardType cardType;
    private boolean disable;

    @ManyToOne(fetch = FetchType.EAGER)  // this is the inverse of an OneToMany relationship     // tells JPA to make sure that when an account is loaded from the database, the client/owner data should be loaded too
    @JoinColumn(name="client_id")    //For each row of the account database table, include a column client_id with the ID of the owner of the account.
    private Client client;

    public Card (){}

    public Card(CardType cardType,CardColor cardColor, Client client, LocalDate fromDate, LocalDate thruDate, String number, int cvv, boolean disable) {
        this.cardType = cardType;
        this.cardColor = cardColor;
        this.client = client;
        this.cardholder = client.getFullName();
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.number = number;
        this.cvv = cvv;
        this.disable = disable;
    }

    public long getId() {
        return id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
