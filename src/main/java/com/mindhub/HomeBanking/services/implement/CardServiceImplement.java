package com.mindhub.HomeBanking.services.implement;

import com.mindhub.HomeBanking.dtos.CardDTO;
import com.mindhub.HomeBanking.models.Card;
import com.mindhub.HomeBanking.repositories.CardRepository;
import com.mindhub.HomeBanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CardServiceImplement implements CardService {

    @Autowired
    CardRepository cardRepository;


    @Override
    public void saveCard(Card card)
    {
        cardRepository.save(card);
    }

    @Override
    public Card findById(long id) {

        return cardRepository.findById(id).orElse(null);

    }

    @Override
    public List<CardDTO> getCards() {
        return cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toList());
    }


}
