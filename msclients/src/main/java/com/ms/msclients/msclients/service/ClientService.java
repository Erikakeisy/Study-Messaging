package com.ms.msclients.msclients.service;

import com.ms.msclients.msclients.entity.Client;
import com.ms.msclients.msclients.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client save (Client client){
        return clientRepository.save(client);
    }

    public Optional<Client> getByCpf(String cpf){
        return clientRepository.findByCpf(cpf);
    }



}
