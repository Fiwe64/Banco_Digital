package com.Banco.Digital.Controller;

import com.Banco.Digital.domain.cliente.Cliente;
import com.Banco.Digital.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Criar Cliente
    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Listar Clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Atualizar Cliente
    @PutMapping("/{id}")
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

    // Deletar Cliente
    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}
