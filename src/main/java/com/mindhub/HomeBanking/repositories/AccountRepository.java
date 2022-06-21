package com.mindhub.HomeBanking.repositories;

import com.mindhub.HomeBanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource //crea los controladores para el repositorio
public interface AccountRepository extends JpaRepository<Account, Long> { //extends = herencia de propiedades y métodos

    Account findByNumber (String number);

}
