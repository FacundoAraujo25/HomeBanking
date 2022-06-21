package com.mindhub.HomeBanking.models;

import com.mindhub.HomeBanking.dtos.AccountDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;


@Entity  //genera una tabla en la base de datos
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName,lastName,mail,password;

    public Client (){}

    public Client (String firstName,String lastName,String mail,String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getName(){
        return firstName;
    }

    public void setName(String name){
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
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

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    Set<Card> cards = new HashSet<>();

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }
    public void addClientLoan(ClientLoan clientLoan) {
        clientLoan.setClient(this);
        clientLoans.add(clientLoan);
    }

    public Set<Card> getCards() {    //We use Set<Account> rather than List because JPA may retrieve duplicate results from the database as more relationships with clients and accounts are created.
        return cards;
    }

    public void addCard(Card card) {
        card.setClient(this);
        cards.add(card);
    }

    public Set<Account> getAccounts() {    //We use Set<Account> rather than List because JPA may retrieve duplicate results from the database as more relationships with clients and accounts are created.
        return accounts;
    }

    public void addAccount(Account account) {
        account.setClient(this);
        accounts.add(account);     // The addAccount() method lets us connect a client to an account. If we call the line of code
        //then, when addAccount() is called,
        //Java sets the this variable to the instance of Client in client.
        //Whenever a method is called on an instance, Java sets this to the instance.
        //The addAccount() code sets the client/owner of account to that client/owner,
        //The code adds the account to the set of accounts for this client.
    }

    public List<Loan> getLoans() {
        return clientLoans.stream().map(client->client.getLoan()).collect(toList());
    }

}
