package com.Banco.Digital.service;

import com.Banco.Digital.domain.cliente.Cliente;
import com.Banco.Digital.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Criar novo cliente
    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Atualizar cliente
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNome(clienteAtualizado.getNome());
                    clienteExistente.setEmail(clienteAtualizado.getEmail());
                    clienteExistente.setTelefone(clienteAtualizado.getTelefone());
                    return clienteRepository.save(clienteExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
    }

    // Buscar cliente por ID
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
    }

    // Listar todos os clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Deletar cliente
    public void deletarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
        clienteRepository.delete(cliente);
    }
}
