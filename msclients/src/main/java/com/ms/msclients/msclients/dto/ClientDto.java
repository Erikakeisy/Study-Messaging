package com.ms.msclients.msclients.dto;

import com.ms.msclients.msclients.entity.Client;
import lombok.Data;

@Data
public class ClientDto {

    private String cpf;
    private String name;
    private Integer age;

    public Client toModel(){
        return new Client(cpf, name, age);
    }
}
