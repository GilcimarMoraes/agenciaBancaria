package com.desafio.agenciaBancaria.transacoes;


import com.desafio.agenciaBancaria.entity.ContaBancaria;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Transacoes {

    public void depositar( ContaBancaria conta, BigDecimal valor ) {

        conta.creditar( valor);

    }

    public void sacar( ContaBancaria conta, BigDecimal valor ) {

        conta.debitar( valor );
    }


}
