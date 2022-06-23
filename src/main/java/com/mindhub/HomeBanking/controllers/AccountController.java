package com.mindhub.HomeBanking.controllers;


import com.mindhub.HomeBanking.dtos.AccountDTO;
import com.mindhub.HomeBanking.models.Account;
import com.mindhub.HomeBanking.models.AccountType;
import com.mindhub.HomeBanking.models.Client;
import com.mindhub.HomeBanking.repositories.AccountRepository;
import com.mindhub.HomeBanking.repositories.ClientRepository;
import com.mindhub.HomeBanking.services.AccountService;
import com.mindhub.HomeBanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.HomeBanking.toolsBox.utils.getRandomAccountNumber;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;


    @RequestMapping("/accounts") //asigna ruta a un controlador específico
    public List<AccountDTO> getAccounts() {
        //return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
//La función stream permite procesar y transformar (con map) cada elemento de la lista que retorna findAll(), map ejecuta el constructor de la clase ClientDTO y le pasa como parámetro el elemento Client que está procesando en ese momento

//se puede simplificar un poco, como lo único que hace map es ejecutar el método constructor que está en ClientDTO, se puede indicar la referencia a ese método sin tener que declarar toda una expresión lambda:
        //return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());

        return accountService.getAccountsDto();

    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){

        //return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
        return accountService.getAccount(id);

    }




    @RequestMapping("/clients/current/accounts/{id}")
    public AccountDTO getCurrentAccount(@PathVariable Long id,Authentication authentication){

        Client client =  clientService.findByMail(authentication.getName());
        Account currentAccount = accountService.findById(id);

        if(!client.getAccounts().contains(currentAccount))
        {
            return null;
        }

        return new AccountDTO(currentAccount);

    }

    @RequestMapping("/clients/current/accounts")
    public List<Account> getCurrentAccounts(Authentication authentication) {
        Client client =  clientService.findByMail(authentication.getName());

        return client.getAccounts().stream().filter(account -> account.isDisable()==false).collect(toList());

    }



    @PostMapping("/clients/current/accounts")

    public ResponseEntity<Object> newAccount (Authentication authentication,
    @RequestParam AccountType accountType){

    Client client =  clientService.findByMail(authentication.getName());

    String randomAccountNumber = "VIN-" + getRandomAccountNumber(99999999,1); //MI PEQUEÑO GRAN ORGULLO


        if (client.getAccounts().size() >= 3) {

            return new ResponseEntity<>("You are not allowed to create more accounts.", HttpStatus.FORBIDDEN);

        }



        accountService.saveAccount(new Account(randomAccountNumber,0, LocalDateTime.now(),client,false,accountType));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @PatchMapping("/clients/current/accounts")
    public ResponseEntity<Object> disableAccount (Authentication authentication,
                                               @RequestParam long id){


        Account disableAccount = accountService.findById(id);
        Client client =  clientService.findByMail(authentication.getName());


        if(!client.getAccounts().contains(disableAccount))
        {
            return new ResponseEntity<>("This account cannot be deleted because it is not yours", HttpStatus.FORBIDDEN);
        }

        disableAccount.setDisable(true);
        accountService.saveAccount(disableAccount);


        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}



