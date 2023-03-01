package com.ms.mscard.mscard.service;

import com.ms.mscard.mscard.entities.ClientCard;
import com.ms.mscard.mscard.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository clientCardRepository;

    public List<ClientCard> listCardsByCpf(String cpf){
        return clientCardRepository.findByCpf(cpf);
    }
}
