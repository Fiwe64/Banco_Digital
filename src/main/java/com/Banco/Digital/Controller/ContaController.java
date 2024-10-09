package com.Banco.Digital.Controller;

import com.Banco.Digital.domain.cliente.Cliente;
import com.Banco.Digital.domain.conta.Conta;
import com.Banco.Digital.domain.conta.ContaCorrente;
import com.Banco.Digital.domain.conta.ContaPoupanca;
import com.Banco.Digital.domain.conta.ContaSalario;
import com.Banco.Digital.repository.ClienteRepository;
import com.Banco.Digital.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    // Criar Conta Genérica
    @PostMapping
    public Conta criarConta(@RequestBody Conta conta) {
        Cliente clienteExistente = clienteRepository.findById(conta.getCliente().getId()).orElse(null);

        if (clienteExistente != null) {
            clienteExistente.setNome(conta.getCliente().getNome());
            clienteExistente.setEmail(conta.getCliente().getEmail());
            clienteExistente.setTelefone(conta.getCliente().getTelefone());
            clienteRepository.save(clienteExistente);
            conta.setCliente(clienteExistente);
        }

        return contaRepository.save(conta);
    }

    // Listar Contas
    @GetMapping
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    // Criar Conta Corrente
    @PostMapping("/corrente")
    public Conta criarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
        return contaRepository.save(contaCorrente);
    }

    // Criar Conta Poupança
    @PostMapping("/poupanca")
    public Conta criarContaPoupanca(@RequestBody ContaPoupanca contaPoupanca) {
        return contaRepository.save(contaPoupanca);
    }

    // Criar Conta Salário
    @PostMapping("/salario")
    public Conta criarContaSalario(@RequestBody ContaSalario contaSalario) {
        return contaRepository.save(contaSalario);
    }

    // Atualizar Conta
    @PutMapping("/{id}")
    public Conta atualizarConta(@PathVariable Long id, @RequestBody Conta conta) {
        return contaRepository.findById(id)
                .map(contaExistente -> {
                    contaExistente.setSaldo(conta.getSaldo());
                    contaExistente.setCliente(conta.getCliente());
                    return contaRepository.save(contaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    // Deletar Conta
    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable Long id) {
        contaRepository.deleteById(id);
    }

    // Visualizar Saldo da Conta
    @GetMapping("/{id}/saldo")
    public Double visualizarSaldo(@PathVariable Long id) {
        return contaRepository.findById(id)
                .map(Conta::getSaldo)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
}
