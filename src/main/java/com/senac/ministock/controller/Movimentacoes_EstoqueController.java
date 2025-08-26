package com.senac.ministock.controller;

import com.senac.ministock.entity.Movimentacoes_Estoque;
import com.senac.ministock.service.Movimentacoes_EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/movimentacoes-estoque")
public class Movimentacoes_EstoqueController {
    private Movimentacoes_EstoqueService movimentacoesEstoqueService;

    public Movimentacoes_EstoqueController(Movimentacoes_EstoqueService movimentacoesEstoqueService) {
        this.movimentacoesEstoqueService = movimentacoesEstoqueService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Movimentacoes_Estoque>> listarMovimentacoesEstoque() {
        return ResponseEntity.ok(movimentacoesEstoqueService.listarMovimentacoesEstoque());
    }
}