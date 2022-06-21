package com.mindhub.HomeBanking.controllers;

import com.mindhub.HomeBanking.dtos.CardDTO;
import com.mindhub.HomeBanking.dtos.LoanDTO;
import com.mindhub.HomeBanking.models.Card;
import com.mindhub.HomeBanking.models.CardColor;
import com.mindhub.HomeBanking.models.CardType;
import com.mindhub.HomeBanking.models.Client;
import com.mindhub.HomeBanking.repositories.CardRepository;
import com.mindhub.HomeBanking.repositories.ClientRepository;
import com.mindhub.HomeBanking.services.CardService;
import com.mindhub.HomeBanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.HomeBanking.models.CardType.CREDIT;
import static com.mindhub.HomeBanking.models.CardType.DEBIT;
import static com.mindhub.HomeBanking.toolsBox.utils.getCardRandomNumber;
import static com.mindhub.HomeBanking.toolsBox.utils.getRandomAccountNumber;

@RestController
@RequestMapping("/api")
public class CardController {


    @Autowired
    CardService cardService;
    @Autowired
    ClientService clientService;


    @GetMapping("/clients/current/cards")
    public List<CardDTO> getCards(){return cardService.getCards();
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> newCard (Authentication authentication,

            @RequestParam CardType cardType, @RequestParam CardColor cardColor){


        Client client =  clientService.findByMail(authentication.getName());

        Set<Card> debitCards = client.getCards().stream().filter(card -> card.getCardType()==DEBIT && !card.isDisable()).collect(Collectors.toSet());

        Set<Card> creditCards = client.getCards().stream().filter(card -> card.getCardType()==CREDIT && !card.isDisable()).collect(Collectors.toSet());


        if(creditCards.size() >=3 && cardType == CREDIT)
        {
            return new ResponseEntity<>("You are not allowed to ask for more cards.", HttpStatus.FORBIDDEN);
        }
        if(debitCards.size() >= 3 && cardType == DEBIT)
        {
            return new ResponseEntity<>("You are not allowed to ask for more cards.", HttpStatus.FORBIDDEN);
        }

        cardService.saveCard(new Card(cardType,cardColor,client, LocalDate.now(),LocalDate.now().plusYears(5),getCardRandomNumber(1000000000000000L,9999999999999999L),getRandomAccountNumber(100,999),false));
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PatchMapping("/clients/current/cards")
    public ResponseEntity<Object> disableCard (Authentication authentication,
            @RequestParam long id){


        Card disableCard = cardService.findById(id);
        Client client =  clientService.findByMail(authentication.getName());


        if(!client.getCards().contains(disableCard))
        {
            return new ResponseEntity<>("This card cannot be deleted because it is not yours", HttpStatus.FORBIDDEN);
        }

        disableCard.setDisable(true);
        cardService.saveCard(disableCard);


        return new ResponseEntity<>(HttpStatus.CREATED);

    }




}
