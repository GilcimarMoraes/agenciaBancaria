package com.desafio.agenciaBancaria.repository;

import com.desafio.agenciaBancaria.entity.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria,Long> {

    List<ContaBancaria> findByTitularId(Long pessoaId );

    boolean existsByAgenciaAndNumero( String agencia, String numero );
}
