package com.mindhub.HomeBanking.controllers;


import com.mindhub.HomeBanking.dtos.AccountDTO;
import com.mindhub.HomeBanking.dtos.TransactionDTO;
import com.mindhub.HomeBanking.models.Account;
import com.mindhub.HomeBanking.models.Client;
import com.mindhub.HomeBanking.models.Transaction;
import com.mindhub.HomeBanking.models.TransactionType;
import com.mindhub.HomeBanking.repositories.AccountRepository;
import com.mindhub.HomeBanking.repositories.ClientRepository;
import com.mindhub.HomeBanking.repositories.TransactionRepository;
import com.mindhub.HomeBanking.services.AccountService;
import com.mindhub.HomeBanking.services.ClientService;
import com.mindhub.HomeBanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.HomeBanking.toolsBox.utils.getRandomAccountNumber;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountService accountService;
    @Autowired
    ClientService clientService;


    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> newTransaction ( Authentication authentication,

            @RequestParam double amount, @RequestParam String description,

            @RequestParam String sourceAccountNumber, @RequestParam String destinationAccountNumber) {


        Account sourceAccount = accountService.findByNumber(sourceAccountNumber);
        Account destinationAccount = accountService.findByNumber(destinationAccountNumber);

        Client client =  clientService.findByMail(authentication.getName());

        double currentBalanceCredit = destinationAccount.getBalance() + amount;

        double currentBalanceDebit = sourceAccount.getBalance() - amount;


        if (amount == 0.0 || description.isEmpty() || sourceAccountNumber.isEmpty() || destinationAccountNumber.isEmpty())
        {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (sourceAccountNumber.equals(destinationAccountNumber))
        {
            return new ResponseEntity<>("You cannot transfer money to the same account.", HttpStatus.FORBIDDEN);
        }

        if (sourceAccount == null) {

            return new ResponseEntity<>("The source account does not exist.", HttpStatus.FORBIDDEN);

        }

        if(!client.getAccounts().contains(sourceAccount))
        {
            return new ResponseEntity<>("This client is not the owner of this account.", HttpStatus.FORBIDDEN);
        }

        if (destinationAccount == null)
        {
            return new ResponseEntity<>("The destination account does not exist.", HttpStatus.FORBIDDEN);
        }


        if (sourceAccount.getBalance()<amount)
        {
            return new ResponseEntity<>("There is not enough money to complete the transaction.", HttpStatus.FORBIDDEN);
        }

        if (amount < 0 )
        {
            return new ResponseEntity<>("The amount of money to transfer is negative", HttpStatus.FORBIDDEN);
        }


        Transaction transactionSource = new Transaction(TransactionType.DEBIT,amount,description + ".- To:" + destinationAccountNumber,LocalDateTime.now(),sourceAccount,currentBalanceDebit);
        Transaction transactionDestination = new Transaction(TransactionType.CREDIT,amount,description + ".- From:" + sourceAccountNumber,LocalDateTime.now(),destinationAccount,currentBalanceCredit);
        transactionService.saveTransaction(transactionSource);
        transactionService.saveTransaction(transactionDestination);

        destinationAccount.setBalance(currentBalanceCredit);
        sourceAccount.setBalance(currentBalanceDebit);


        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
