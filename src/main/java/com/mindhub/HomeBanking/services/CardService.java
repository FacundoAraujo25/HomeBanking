package com.mindhub.HomeBanking.services;

import com.mindhub.HomeBanking.dtos.CardDTO;
import com.mindhub.HomeBanking.models.Card;

import java.util.List;

public interface CardService {

    void saveCard (Card card);

    Card findById (long id);

    List<CardDTO> getCards();

}
