package com.Banco.Digital.domain.conta;

import com.Banco.Digital.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta")
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
        return this.getClass().getSimpleName();
    }
}
