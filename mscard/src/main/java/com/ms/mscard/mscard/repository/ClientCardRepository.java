package com.ms.mscard.mscard.repository;

import com.ms.mscard.mscard.entities.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long>{

    List<ClientCard> findByCpf(String cpf);
}
