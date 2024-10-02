package com.Banco.Digital.domain.conta;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CORRENTE")
@NoArgsConstructor
public class ContaCorrente extends Conta {

    @Override
    public void depositar(Double valor) {
        setSaldo(getSaldo() + valor);
    }

    @Override
    public void sacar(Double valor) {
        if (getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
    }
}
