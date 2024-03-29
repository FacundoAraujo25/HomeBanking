package com.mindhub.HomeBanking.services.implement;

import com.mindhub.HomeBanking.models.Transaction;
import com.mindhub.HomeBanking.repositories.TransactionRepository;
import com.mindhub.HomeBanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
