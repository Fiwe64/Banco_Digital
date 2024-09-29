package com.Banco.Digital.Controller;

import com.Banco.Digital.domain.cliente.Cliente;
import com.Banco.Digital.domain.conta.Conta;
import com.Banco.Digital.domain.conta.ContaCorrente;
import com.Banco.Digital.domain.conta.ContaPoupanca;
import com.Banco.Digital.domain.conta.ContaSalario;
import com.Banco.Digital.domain.pagamento.Pagamento;
import com.Banco.Digital.repository.ClienteRepository;
import com.Banco.Digital.repository.ContaRepository;
import com.Banco.Digital.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banco")
public class BancoController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @PostMapping("/clientes")
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PostMapping("/contas")
    public Conta criarConta(@RequestBody Conta conta) {
        // Verifica se o cliente já existe
        Cliente clienteExistente = clienteRepository.findById(conta.getCliente().getId()).orElse(null);

        if (clienteExistente != null) {
            // Atualiza os dados do cliente, se necessário
            if (conta.getCliente().getNome() != null) {
                clienteExistente.setNome(conta.getCliente().getNome());
            }
            if (conta.getCliente().getEmail() != null) {
                clienteExistente.setEmail(conta.getCliente().getEmail());
            }
            if (conta.getCliente().getTelefone() != null) {
                clienteExistente.setTelefone(conta.getCliente().getTelefone());
            }

            // Salva o cliente atualizado
            clienteRepository.save(clienteExistente);

            // Atribui o cliente existente atualizado à conta
            conta.setCliente(clienteExistente);
        }

        // Salva a conta
        return contaRepository.save(conta);
    }

    @PostMapping("/pagamentos")
    public Pagamento realizarPagamento(@RequestBody Pagamento pagamento) {
        // Verifica se a conta já existe
        Conta contaExistente = contaRepository.findById(pagamento.getConta().getId()).orElse(null);

        if (contaExistente != null) {
            // Atribui a conta existente ao pagamento
            pagamento.setConta(contaExistente);
            return pagamentoRepository.save(pagamento);
        } else {
            throw new RuntimeException("Conta não encontrada");
        }
    }

    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/contas")
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    @GetMapping("/pagamentos")
    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    @PostMapping("/contas/corrente")
    public Conta criarContaCorrente(@RequestBody ContaCorrente contaCorrente) {
        return contaRepository.save(contaCorrente);
    }

    @PostMapping("/contas/poupanca")
    public Conta criarContaPoupanca(@RequestBody ContaPoupanca contaPoupanca) {
        return contaRepository.save(contaPoupanca);
    }

    @PostMapping("/contas/salario")
    public Conta criarContaSalario(@RequestBody ContaSalario contaSalario) {
        return contaRepository.save(contaSalario);
    }
    //metodo para atualizar cliente
    @PutMapping("/clientes/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNome(cliente.getNome());
                    clienteExistente.setEmail(cliente.getEmail());
                    clienteExistente.setTelefone(cliente.getTelefone());
                    return clienteRepository.save(clienteExistente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    //metodo para deletar cliente
    @DeleteMapping("/clientes/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }

    // metodo para atualizar Conta
    @PutMapping("/contas/{id}")
    public Conta atualizarConta(@PathVariable Long id, @RequestBody Conta conta) {
        return contaRepository.findById(id)
                .map(contaExistente -> {
                    contaExistente.setSaldo(conta.getSaldo());
                    contaExistente.setCliente(conta.getCliente()); // Se você quiser atualizar o cliente
                    return contaRepository.save(contaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    //metodo para deletar conta
    @DeleteMapping("/contas/{id}")
    public void deletarConta(@PathVariable Long id) {
        contaRepository.deleteById(id);
    }


    @PutMapping("/pagamentos/{id}")
    public Pagamento atualizarPagamento(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        return pagamentoRepository.findById(id)
                .map(pagamentoExistente -> {
                    pagamentoExistente.setValor(pagamento.getValor());
                    pagamentoExistente.setConta(pagamento.getConta()); // Se você quiser atualizar a conta
                    return pagamentoRepository.save(pagamentoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    //metodo para deletar pagamento
    @DeleteMapping("/pagamentos/{id}")
    public void deletarPagamento(@PathVariable Long id) {
        pagamentoRepository.deleteById(id);
    }
}
