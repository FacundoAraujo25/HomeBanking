package com.mindhub.HomeBanking.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindhub.HomeBanking.models.Card;
import com.mindhub.HomeBanking.models.CardColor;
import com.mindhub.HomeBanking.models.CardType;

import java.time.LocalDate;

public class CardDTO {

    private long id;
    private String cardholder;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    private LocalDate fromDate,thruDate;
    private String number;
    private int cvv;
    private CardColor cardColor;
    private CardType cardType;
    private boolean disable;


    public CardDTO (){}

    public CardDTO (Card card){
        this.id = card.getId();
        this.cardType = card.getCardType();
        this.cardColor = card.getCardColor();
        this.cardholder = card.getCardholder();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.disable = card.isDisable();
    }

    public long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
