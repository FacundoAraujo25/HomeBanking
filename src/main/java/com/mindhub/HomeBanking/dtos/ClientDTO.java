package com.mindhub.HomeBanking.dtos;
import com.mindhub.HomeBanking.models.Card;
import com.mindhub.HomeBanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import static java.util.stream.Collectors.toSet;

public class ClientDTO {
//patrón de diseño llamado DTO (Data Transfer Objects) = indica que se deben crear clases cuyo único propósito es tener las propiedades que se desean enviar o recibir en los servicios web
    private String firstName,lastName,mail;
    private long id;
    Set<ClientLoanDTO> loans = new HashSet<>();

    Set<AccountDTO> accounts = new HashSet<>();

    Set<CardDTO> cards = new HashSet<>();

    public ClientDTO(){}
    public ClientDTO(Client client) {

        this.id = client.getId();
        this.firstName = client.getName();
        this.lastName = client.getLastName();
        this.mail = client.getMail();
        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(toSet());
        this.loans = client.getClientLoans().stream().map(loan->new ClientLoanDTO(loan)).collect(toSet());
        this.cards = client.getCards().stream().map(card -> new CardDTO(card)).collect(toSet());
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public Set<ClientLoanDTO> getClientLoans() {
        return loans;
    }

    public void setLoans(Set<ClientLoanDTO> loans) {
        this.loans = loans;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardDTO> cards) {
        this.cards = cards;
    }
}
