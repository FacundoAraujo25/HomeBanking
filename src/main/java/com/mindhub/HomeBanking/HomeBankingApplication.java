package com.mindhub.HomeBanking;

import com.mindhub.HomeBanking.models.*;
import com.mindhub.HomeBanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.HomeBanking.models.TransactionType.CREDIT;
import static com.mindhub.HomeBanking.models.TransactionType.DEBIT;

import static com.mindhub.HomeBanking.models.CardColor.TITANIUM;
import static com.mindhub.HomeBanking.models.CardColor.SILVER;
import static com.mindhub.HomeBanking.models.CardColor.GOLD;

@SpringBootApplication
public class HomeBankingApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;



    public static void main(String[] args) {

        SpringApplication.run(HomeBankingApplication.class, args);

    }

    @Bean
    public CommandLineRunner initData(ClientRepository repositoryClient, AccountRepository repositoryAccount, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
        return (args) -> {

            // save a couple of customers
            Client client1 = new Client("Melba","Morel","melba@mindhub.com", passwordEncoder.encode("Melbita123"));
            repositoryClient.save(client1);

            Client client2 = new Client("José","Álvarez","jalvarez@mindhub.com", passwordEncoder.encode("Josecito123"));
            repositoryClient.save(client2);

            Client client3 = new Client("Pablo","López","plopez@mindhub.com", passwordEncoder.encode("Pablito123"));
            repositoryClient.save(client3);

            Client admin = new Client("admin","admin","admin@admin.com",passwordEncoder.encode("Admin123"));
            repositoryClient.save(admin);

            Account account1 = new Account("VIN001",5000, LocalDateTime.now(),client1,false,AccountType.SAVING);
            Account account2 = new Account("VIN002",7500, LocalDateTime.now().plusDays(1),client1,false,AccountType.CURRENT);
            Account account3 = new Account("VIN003",2500, LocalDateTime.now().plusDays(2),client2,false,AccountType.SAVING);
            Account account4 = new Account("VIN004",3500, LocalDateTime.now(),client2,false,AccountType.CURRENT);

            repositoryAccount.save(account1);
            repositoryAccount.save(account2);
            repositoryAccount.save(account3);
            repositoryAccount.save(account4);

            Transaction transaction1 = new Transaction(DEBIT,2500,"Revista AVON",LocalDateTime.now(),account1,account1.getBalance()-2500);
            //Transaction transaction2 = new Transaction(CREDIT,375,"Cuota MindHub",LocalDateTime.now().minusDays(1),account4);
            //Transaction transaction3 = new Transaction(DEBIT,72,"Nintendo DS",LocalDateTime.now().plusDays(2),account3);
            //Transaction transaction4 = new Transaction(DEBIT,5750,"BankrdoX",LocalDateTime.now(),account2);
            //Transaction transaction5 = new Transaction(CREDIT,1310,"Suscripción premium MindHubNeta",LocalDateTime.now().plusDays(1),account1);
            //Transaction transaction6 = new Transaction(DEBIT,3210,"Suscripción premium MindHubNeta",LocalDateTime.now(),account1);
            //Transaction transaction7 = new Transaction(CREDIT,470,"Suscripción premium MindHubNeta",LocalDateTime.now(),account2);
            //Transaction transaction8 = new Transaction(DEBIT,720,"Suscripción premium MindHubNeta",LocalDateTime.now(),account2);

            transactionRepository.save(transaction1);
//            transactionRepository.save(transaction2);
//            transactionRepository.save(transaction3);
//            transactionRepository.save(transaction4);
//            transactionRepository.save(transaction5);
//            transactionRepository.save(transaction6);
//            transactionRepository.save(transaction7);
//            transactionRepository.save(transaction8);

            Loan loan1 = new Loan("Hipotecario",500000, List.of(12,24,36,48,60),7.5f);
            Loan loan2 = new Loan("Personal",100000, List.of(6,12,24),3.9f);
            Loan loan3 = new Loan("Automotriz",300000, List.of(6,12,24,36),5.7f);


            loanRepository.save(loan1);
            loanRepository.save(loan2);
            loanRepository.save(loan3);


            ClientLoan clientLoan1 = new ClientLoan(400000,60,client1,loan1);
            ClientLoan clientLoan2 = new ClientLoan(50000,12,client1,loan2);

            ClientLoan clientLoan3 = new ClientLoan(100000,24,client2,loan2);
            ClientLoan clientLoan4 = new ClientLoan(200000,36,client2,loan3);

            clientLoanRepository.save(clientLoan1);
            clientLoanRepository.save(clientLoan2);
            clientLoanRepository.save(clientLoan3);
            clientLoanRepository.save(clientLoan4);

            Card card1 = new Card(CardType.DEBIT,GOLD,client1, LocalDate.now(),LocalDate.now().plusYears(5),"1234432112344321",319,false);
            Card card2 = new Card(CardType.CREDIT,TITANIUM,client1, LocalDate.now(),LocalDate.now().plusYears(5),"4123123441231234",511,false);
            Card card3 = new Card(CardType.CREDIT,SILVER,client1,LocalDate.now(),LocalDate.now().plusYears(3),"1321423152356446",715,false);

            Card card4 = new Card(CardType.CREDIT,SILVER,client2, LocalDate.now().plusMonths(3),LocalDate.now().plusYears(4),"1243423112434231",193,false);

            cardRepository.save(card1);
            cardRepository.save(card2);
            cardRepository.save(card3);
            cardRepository.save(card4);

        };
    }






}
