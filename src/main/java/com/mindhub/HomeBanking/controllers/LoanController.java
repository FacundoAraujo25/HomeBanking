package com.mindhub.HomeBanking.controllers;


import com.mindhub.HomeBanking.dtos.ClientLoanDTO;
import com.mindhub.HomeBanking.dtos.LoanApplicationDTO;
import com.mindhub.HomeBanking.dtos.LoanDTO;
import com.mindhub.HomeBanking.models.*;
import com.mindhub.HomeBanking.repositories.*;
import com.mindhub.HomeBanking.services.AccountService;
import com.mindhub.HomeBanking.services.ClientService;
import com.mindhub.HomeBanking.services.LoanService;
import com.mindhub.HomeBanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {


    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ClientLoanRepository clientLoanRepository;


    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    LoanService loanService;
    @Autowired
    TransactionService transactionService;




    @GetMapping("/loans")
    public List<LoanDTO> getLoans(){
        return loanService.getLoans();
    }




    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> newLoan
            (@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){


        Loan loan = loanService.findById(loanApplicationDTO.getIdLoan());

        Account loanAccount = accountService.findByNumber(loanApplicationDTO.getAccountNumber());

        Client client = clientService.findByMail(authentication.getName());

        float loanInterest = 1 + loan.getPctInterest()/100; //Daph

        double currentBalance = loanAccount.getBalance() + loanApplicationDTO.getAmount();

        int loanInterestpct = (int) (loanApplicationDTO.getAmount()*loanInterest);


        if (loanApplicationDTO.getAmount() < 10000 || loanApplicationDTO.getPayments() == 0 || loanApplicationDTO.getIdLoan() == 0)
        {
            return new ResponseEntity<>("Missing data or invalid id", HttpStatus.FORBIDDEN);
        }

        if (loan == null)
        {
            return new ResponseEntity<>("This loan does not exist.", HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getAmount() > loan.getMaxAmount())
        {
            return new ResponseEntity<>("The amount is bigger than the max amount.", HttpStatus.FORBIDDEN);
        }

        if(!loan.getPayments().contains(loanApplicationDTO.getPayments()))
        {
            return new ResponseEntity<>("The payments you ask for are not allowed in this type of loan", HttpStatus.FORBIDDEN);
        }

        if (loanAccount == null)
        {
            return new ResponseEntity<>("The account of the loan does not exist.", HttpStatus.FORBIDDEN);
        }

        if (!client.getAccounts().contains(loanAccount))
        {
            return new ResponseEntity<>("The account is not from the authenticated client.", HttpStatus.FORBIDDEN);
        }



        ClientLoan clientLoan = new ClientLoan(loanInterestpct,loanApplicationDTO.getPayments(),client,loan);
        loanService.saveClientLoan(clientLoan);


        Transaction loanTransaction = new Transaction(TransactionType.CREDIT,loanApplicationDTO.getAmount(), loan.getName() +  ".- Loan approved", LocalDateTime.now(),loanAccount,currentBalance);
        transactionService.saveTransaction(loanTransaction);

        loanAccount.setBalance(loanAccount.getBalance()+loanApplicationDTO.getAmount());


        return new ResponseEntity<>(HttpStatus.CREATED);

    }



}













