package com.Banco.Digital.Controller;

import com.Banco.Digital.domain.cliente.Cliente;
import com.Banco.Digital.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Criar novo cliente
    @PostMapping
    public Cliente criarCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }

    // Atualizar cliente existente
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    // Listar todos os clientes
    @GetMapping
    public List<Cliente> listarTodosClientes() {
        return clienteService.listarTodos();
    }

    // Deletar cliente
    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
    }
}

