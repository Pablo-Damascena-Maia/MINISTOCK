package com.senac.ministock.controller;


import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTORequest;
import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTOUpdateRequest;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOResponse;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOUpdateResponse;
import com.senac.ministock.entity.Movimentacoes_Estoque;
import com.senac.ministock.service.Movimentacoes_EstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimentacao_estoque")
public class Movimentacao_EstoqueController {

    private Movimentacoes_EstoqueService service;

    public void MovimentacaoEstoqueController(Movimentacoes_EstoqueService service) {
        this.service = service;
    }

    public Movimentacao_EstoqueController(Movimentacoes_EstoqueService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Movimentacoes_EstoqueDTOResponse>> listar() {
        return ResponseEntity.ok(service.listarMovimentacoes());
    }

    @GetMapping("/listar_por_id/{movimentacao_id}")
    public ResponseEntity<Movimentacoes_EstoqueDTOResponse> listarPorId(@PathVariable("movimentacao_id") Integer id) {
        Movimentacoes_EstoqueDTOResponse m = service.listarPorId(id);
        return (m != null) ? (ResponseEntity<Movimentacoes_EstoqueDTOResponse>) ResponseEntity.ok() : ResponseEntity.noContent().build();
    }

    @PostMapping("/criar")
    public ResponseEntity<Movimentacoes_EstoqueDTOResponse> criar(@Valid @RequestBody Movimentacoes_EstoqueDTORequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarMovimentacao(dto));
    }

    @PutMapping("/atualizar/{movimentacao_id}")
    public ResponseEntity<Movimentacoes_EstoqueDTOResponse> atualizar(
            @PathVariable("movimentacao_id") Integer id,
            @Valid @RequestBody Movimentacoes_EstoqueDTOUpdateRequest dto) {
        return ResponseEntity.ok(service.atualizarMovimentacao(id, dto));
    }

    @PatchMapping("/atualizar_status/{movimentacao_id}")
    public ResponseEntity<Movimentacoes_EstoqueDTOUpdateResponse> atualizarStatus(
            @PathVariable("movimentacao_id") Integer id,
            @Valid @RequestBody Movimentacoes_EstoqueDTORequest dto) {
        return ResponseEntity.ok(service.atualizarStatusMovimentacao(id, dto));
    }

    @DeleteMapping("/apagar/{movimentacao_id}")
    public ResponseEntity<Void> apagar(@PathVariable("movimentacao_id") Integer id) {
        service.apagarMovimentacao(id);
        return ResponseEntity.noContent().build();
    }
}