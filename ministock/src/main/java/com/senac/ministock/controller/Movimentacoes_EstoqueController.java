package com.senac.ministock.controller;

import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTORequest;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOResponse;
import com.senac.ministock.service.Movimentacoes_EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes_estoque")
public class Movimentacoes_EstoqueController {

    private final Movimentacoes_EstoqueService service;

    public Movimentacoes_EstoqueController(Movimentacoes_EstoqueService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<Movimentacoes_EstoqueDTOResponse> criar(@RequestBody Movimentacoes_EstoqueDTORequest dto) {
        Movimentacoes_EstoqueDTOResponse resposta = service.criarMovimentacao(dto);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Movimentacoes_EstoqueDTOResponse>> listar() {
        List<Movimentacoes_EstoqueDTOResponse> lista = service.listarMovimentacoes();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Movimentacoes_EstoqueDTOResponse> atualizar(
            @PathVariable int id,
            @RequestBody Movimentacoes_EstoqueDTORequest dto) {
        Movimentacoes_EstoqueDTOResponse resposta = service.atualizarMovimentacao(id, dto);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        service.removerMovimentacao(id);
        return ResponseEntity.noContent().build();
    }
}
