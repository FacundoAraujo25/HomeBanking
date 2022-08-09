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

            Client client4 = new Client("Marta","Gonzalez","mgonzalez@mindhub.com", passwordEncoder.encode("Martita123"));
            repositoryClient.save(client4);

            Client client5 = new Client("Jorge","Ramones","jramones@mindhub.com", passwordEncoder.encode("Jorgito123"));
            repositoryClient.save(client5);
            Client admin = new Client("admin","admin","admin@admin.com",passwordEncoder.encode("Admin123"));
            repositoryClient.save(admin);

            Account account1 = new Account("VIN-748395",5000, LocalDateTime.now(),client1,false,AccountType.SAVING);
            Account account2 = new Account("VIN-702258",7500, LocalDateTime.now().plusDays(1),client1,false,AccountType.CURRENT);
            Account account3 = new Account("VIN-266543",2500, LocalDateTime.now().plusDays(2),client2,false,AccountType.SAVING);
            Account account4 = new Account("VIN-231343",3500, LocalDateTime.now(),client2,false,AccountType.CURRENT);
            Account account5 = new Account("VIN-104410",5000, LocalDateTime.now(),client3,false,AccountType.SAVING);
            Account account6 = new Account("VIN-900915",7500, LocalDateTime.now().plusDays(1),client3,false,AccountType.CURRENT);
            Account account7 = new Account("VIN-620728",2500, LocalDateTime.now().plusDays(2),client4,false,AccountType.SAVING);
            Account account8 = new Account("VIN-374807",3500, LocalDateTime.now(),client4,false,AccountType.CURRENT);
            Account account9 = new Account("VIN-342520",5000, LocalDateTime.now(),client5,false,AccountType.SAVING);
            Account account10 = new Account("VIN-649006",7500, LocalDateTime.now().plusDays(1),client5,false,AccountType.CURRENT);

            repositoryAccount.save(account1);
            repositoryAccount.save(account2);
            repositoryAccount.save(account3);
            repositoryAccount.save(account4);
            repositoryAccount.save(account5);
            repositoryAccount.save(account6);
            repositoryAccount.save(account7);
            repositoryAccount.save(account8);
            repositoryAccount.save(account9);
            repositoryAccount.save(account10);

            Transaction transaction1 = new Transaction(DEBIT,2500,"Revista AVON",LocalDateTime.now(),account1,account1.getBalance()-2500);
            Transaction transaction2 = new Transaction(CREDIT,375,"Cuota MindHub",LocalDateTime.now().minusDays(1),account4,account4.getBalance()-375);
            Transaction transaction4 = new Transaction(DEBIT,5750,"BankrdoX",LocalDateTime.now(),account2,account2.getBalance()-5750);
            Transaction transaction5 = new Transaction(CREDIT,1310,"Suscripción premium MindHubNeta",LocalDateTime.now().plusDays(1),account3,account3.getBalance()+1310);
            Transaction transaction6 = new Transaction(DEBIT,3210,"Suscripción premium MindHubNeta",LocalDateTime.now(),account5,account5.getBalance()-3210);
            Transaction transaction7 = new Transaction(CREDIT,470,"Suscripción premium MindHubNeta",LocalDateTime.now(),account6,account6.getBalance()+470);
            Transaction transaction8 = new Transaction(DEBIT,720,"Suscripción premium MindHubNeta",LocalDateTime.now(),account7,account7.getBalance()-720);
            Transaction transaction9 = new Transaction(DEBIT,2500,"Revista AVON",LocalDateTime.now(),account8,account8.getBalance()-2500);
            Transaction transaction10 = new Transaction(CREDIT,375,"Cuota MindHub",LocalDateTime.now().minusDays(1),account9,account9.getBalance()-375);
            Transaction transaction11 = new Transaction(DEBIT,5750,"BankrdoX",LocalDateTime.now(),account10,account10.getBalance()-5750);
            Transaction transaction12 = new Transaction(CREDIT,1310,"Suscripción premium MindHubNeta",LocalDateTime.now().plusDays(1),account1,account1.getBalance()+1310);
            Transaction transaction13 = new Transaction(DEBIT,3210,"Suscripción premium MindHubNeta",LocalDateTime.now(),account2,account2.getBalance()-3210);
            Transaction transaction14 = new Transaction(CREDIT,470,"Suscripción premium MindHubNeta",LocalDateTime.now(),account3,account3.getBalance()+470);
            Transaction transaction15 = new Transaction(DEBIT,720,"Suscripción premium MindHubNeta",LocalDateTime.now(),account4,account4.getBalance()-720);
            Transaction transaction16 = new Transaction(DEBIT,3210,"Suscripción premium MindHubNeta",LocalDateTime.now(),account5,account5.getBalance()-3210);
            Transaction transaction17 = new Transaction(CREDIT,470,"Suscripción premium MindHubNeta",LocalDateTime.now(),account6,account6.getBalance()+470);
            Transaction transaction18 = new Transaction(DEBIT,720,"Suscripción premium MindHubNeta",LocalDateTime.now(),account7,account7.getBalance()-720);
            Transaction transaction19 = new Transaction(DEBIT,2500,"Revista AVON",LocalDateTime.now(),account8,account8.getBalance()-2500);
            Transaction transaction20 = new Transaction(CREDIT,375,"Cuota MindHub",LocalDateTime.now().minusDays(1),account9,account9.getBalance()-375);
            Transaction transaction21 = new Transaction(CREDIT,375,"Cuota MindHub",LocalDateTime.now().minusDays(1),account10,account10.getBalance()-375);

            transactionRepository.save(transaction1);
            transactionRepository.save(transaction2);
            transactionRepository.save(transaction4);
            transactionRepository.save(transaction5);
            transactionRepository.save(transaction6);
            transactionRepository.save(transaction7);
            transactionRepository.save(transaction8);
            transactionRepository.save(transaction9);
            transactionRepository.save(transaction10);
            transactionRepository.save(transaction11);
            transactionRepository.save(transaction12);
            transactionRepository.save(transaction13);
            transactionRepository.save(transaction14);
            transactionRepository.save(transaction15);
            transactionRepository.save(transaction16);
            transactionRepository.save(transaction17);
            transactionRepository.save(transaction18);
            transactionRepository.save(transaction19);
            transactionRepository.save(transaction20);
            transactionRepository.save(transaction21);


            Loan loan1 = new Loan("Hipotecario",500000, List.of(12,24,36,48,60),7.5f);
            Loan loan2 = new Loan("Personal",100000, List.of(6,12,24),3.9f);
            Loan loan3 = new Loan("Automotriz",300000, List.of(6,12,24,36),5.7f);

            loanRepository.save(loan1);
            loanRepository.save(loan2);
            loanRepository.save(loan3);


            ClientLoan clientLoan1 = new ClientLoan(400000,60,client1,loan1);
            ClientLoan clientLoan2 = new ClientLoan(50000,12,client1,loan2);

            ClientLoan clientLoan3 = new ClientLoan(100000,24,client2,loan2);
            ClientLoan clientLoan4 = new ClientLoan(70000,36,client2,loan3);

            ClientLoan clientLoan5 = new ClientLoan(100000,24,client3,loan1);
            ClientLoan clientLoan6 = new ClientLoan(150000,36,client3,loan3);

            ClientLoan clientLoan7 = new ClientLoan(100000,24,client4,loan2);
            ClientLoan clientLoan8 = new ClientLoan(300000,36,client4,loan3);

            ClientLoan clientLoan9 = new ClientLoan(100000,24,client5,loan1);
            ClientLoan clientLoan10 = new ClientLoan(20000,36,client5,loan2);

            clientLoanRepository.save(clientLoan1);
            clientLoanRepository.save(clientLoan2);
            clientLoanRepository.save(clientLoan3);
            clientLoanRepository.save(clientLoan4);
            clientLoanRepository.save(clientLoan5);
            clientLoanRepository.save(clientLoan6);
            clientLoanRepository.save(clientLoan7);
            clientLoanRepository.save(clientLoan8);
            clientLoanRepository.save(clientLoan9);
            clientLoanRepository.save(clientLoan10);

            Card card1 = new Card(CardType.DEBIT,GOLD,client1, LocalDate.now(),LocalDate.now().plusYears(5),"1234432112344321",319,false);
            Card card2 = new Card(CardType.CREDIT,TITANIUM,client1, LocalDate.now(),LocalDate.now().plusYears(5),"4123123441231234",511,false);
            Card card3 = new Card(CardType.CREDIT,SILVER,client1,LocalDate.now(),LocalDate.now().plusYears(3),"1321423152356446",715,false);
            Card card4 = new Card(CardType.CREDIT,SILVER,client2, LocalDate.now().plusMonths(3),LocalDate.now().plusYears(4),"1243423112434231",193,false);
            Card card5 = new Card(CardType.DEBIT,GOLD,client2, LocalDate.now(),LocalDate.now().plusYears(5),"1234432112344321",319,false);
            Card card6 = new Card(CardType.CREDIT,TITANIUM,client3, LocalDate.now(),LocalDate.now().plusYears(5),"4123123441231234",511,false);
            Card card7 = new Card(CardType.DEBIT,SILVER,client3,LocalDate.now(),LocalDate.now().plusYears(3),"1321423152356446",715,false);
            Card card8 = new Card(CardType.CREDIT,SILVER,client4, LocalDate.now().plusMonths(3),LocalDate.now().plusYears(4),"1243423112434231",193,false);
            Card card9 = new Card(CardType.DEBIT,GOLD,client4, LocalDate.now(),LocalDate.now().plusYears(5),"1234432112344321",319,false);
            Card card10 = new Card(CardType.CREDIT,TITANIUM,client5, LocalDate.now(),LocalDate.now().plusYears(5),"4123123441231234",511,false);
            Card card11 = new Card(CardType.DEBIT,SILVER,client5,LocalDate.now(),LocalDate.now().plusYears(3),"1321423152356446",715,false);

            cardRepository.save(card1);
            cardRepository.save(card2);
            cardRepository.save(card3);
            cardRepository.save(card4);
            cardRepository.save(card5);
            cardRepository.save(card6);
            cardRepository.save(card7);
            cardRepository.save(card8);
            cardRepository.save(card9);
            cardRepository.save(card10);
            cardRepository.save(card11);


        };
    }






}
