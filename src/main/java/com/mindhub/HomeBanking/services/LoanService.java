package com.mindhub.HomeBanking.services;

import com.mindhub.HomeBanking.dtos.LoanDTO;
import com.mindhub.HomeBanking.models.ClientLoan;
import com.mindhub.HomeBanking.models.Loan;

import java.util.List;

public interface LoanService {

    List<LoanDTO> getLoans();

    void saveLoan(Loan loan);

    Loan findById(Long id);

    void saveClientLoan(ClientLoan clientLoan);

}
