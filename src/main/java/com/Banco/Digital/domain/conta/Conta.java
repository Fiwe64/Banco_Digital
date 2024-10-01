package com.Banco.Digital.domain.conta;

import com.Banco.Digital.domain.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Conta")
@Table(name = "contas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")  // Inclua "include"
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContaCorrente.class, name = "CORRENTE"),
        @JsonSubTypes.Type(value = ContaPoupanca.class, name = "POUPANCA"),
        @JsonSubTypes.Type(value = ContaSalario.class, name = "SALARIO")
})
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Double saldo;

    public abstract void depositar(Double valor);
    public abstract void sacar(Double valor);

    public String getTipoConta() {
        return this.getClass().getSimpleName(); // Retorna o nome da classe, que é o tipo de conta
    }

}
