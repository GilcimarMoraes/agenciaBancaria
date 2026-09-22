package com.desafio.agenciaBancaria.repository;

import com.desafio.agenciaBancaria.entity.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoContaRepository extends JpaRepository<TipoConta,Long> {
}
