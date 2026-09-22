package com.desafio.agenciaBancaria.repository;

import com.desafio.agenciaBancaria.entity.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria,Long> {
}
