package com.ms.mscard.mscard.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class ClientCard {

    @Id
    @GeneratedValue
    private Long id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;
    private BigDecimal basicLimit;
}
