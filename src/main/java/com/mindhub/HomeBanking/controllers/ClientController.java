package com.mindhub.HomeBanking.controllers;


import com.mindhub.HomeBanking.dtos.ClientDTO;
import com.mindhub.HomeBanking.models.Account;
import com.mindhub.HomeBanking.models.AccountType;
import com.mindhub.HomeBanking.models.Client;
import com.mindhub.HomeBanking.repositories.AccountRepository;
import com.mindhub.HomeBanking.repositories.ClientRepository;
import com.mindhub.HomeBanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.HomeBanking.toolsBox.utils.getRandomAccountNumber;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientService clientService;


    @RequestMapping("/clients") //asigna ruta a un controlador específico
    public List<ClientDTO> getClients() {

        //return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
//La función stream permite procesar y transformar (con map) cada elemento de la lista que retorna findAll(), map ejecuta el constructor de la clase ClientDTO y le pasa como parámetro el elemento Client que está procesando en ese momento

//se puede simplificar un poco, como lo único que hace map es ejecutar el método constructor que está en ClientDTO, se puede indicar la referencia a ese método sin tener que declarar toda una expresión lambda:
        return clientService.getClientsDto();

    }

    @RequestMapping("clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {

        return clientService.getClient(id);

    }


    @RequestMapping("/clients/current")

    public ClientDTO getAll(Authentication authentication) {

        //return clientService.getCurrentClient(authentication.getName());   //getName = getUsernameParameter("mail")

        return new ClientDTO(clientRepository.findByMail(authentication.getName()));

    }


    @Autowired
    public PasswordEncoder passwordEncoder;



    @PostMapping("/clients")

    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String mail, @RequestParam String password) {


        if (firstName.isEmpty() || lastName.isEmpty() || mail.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }



        if (clientService.findByMail(mail) != null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }


        Client client = new Client(firstName, lastName, mail, passwordEncoder.encode(password));
        clientService.saveClient(client);

        String randomAccountNumber = "VIN-" + getRandomAccountNumber(99999999,1); //MI PEQUEÑO GRAN ORGULLO
        accountRepository.save(new Account(randomAccountNumber,0, LocalDateTime.now(),client,false,AccountType.SAVING));


        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
