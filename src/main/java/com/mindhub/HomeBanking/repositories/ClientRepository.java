package com.mindhub.HomeBanking.repositories;

import com.mindhub.HomeBanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource //crea los controladores para el repositorio
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByMail(String mail);


}
