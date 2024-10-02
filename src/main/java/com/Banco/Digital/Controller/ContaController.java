package com.Banco.Digital.controller;

import com.Banco.Digital.domain.conta.Conta;
import com.Banco.Digital.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    // Cadastrar uma nova conta
    @PostMapping
    public Conta cadastrarConta(@RequestBody Conta conta) {
        return contaService.cadastrarConta(conta);
    }

    // Buscar uma conta pelo ID
    @GetMapping("/{id}")
    public Conta buscarContaPorId(@PathVariable Long id) {
        return contaService.buscarContaPorId(id);
    }

    // Listar todas as contas
    @GetMapping
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    // Atualizar uma conta existente pelo ID
    @PutMapping("/{id}")
    public Conta atualizarConta(@PathVariable Long id, @RequestBody Conta conta) {
        return contaService.atualizarConta(id, conta);
    }

    // Deletar uma conta pelo ID
    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable Long id) {
        contaService.deletarConta(id);
    }
}

