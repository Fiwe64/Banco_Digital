package com.Banco.Digital.repository;

import com.Banco.Digital.domain.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {}
