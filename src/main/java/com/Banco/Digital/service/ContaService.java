package com.Banco.Digital.service;

import com.Banco.Digital.domain.conta.Conta;
import com.Banco.Digital.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    // Cadastrar uma nova conta
    public Conta cadastrarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    // Buscar uma conta pelo ID
    public Conta buscarContaPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    // Listar todas as contas
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    // Atualizar uma conta existente
    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        Optional<Conta> contaExistente = contaRepository.findById(id);
        if (contaExistente.isPresent()) {
            Conta conta = contaExistente.get();
            conta.setSaldo(contaAtualizada.getSaldo());
            conta.setCliente(contaAtualizada.getCliente());
            return contaRepository.save(conta);
        } else {
            throw new RuntimeException("Conta não encontrada");
        }
    }

    // Deletar uma conta por ID
    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }
}
