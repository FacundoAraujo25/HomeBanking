package com.mindhub.HomeBanking.services;

import com.mindhub.HomeBanking.dtos.ClientDTO;
import com.mindhub.HomeBanking.models.Client;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getClientsDto();

    ClientDTO getClient(@PathVariable Long id);

    ClientDTO getCurrentClient(String mail);


    Client findByMail(String mail);

    void saveClient(Client client);
}
