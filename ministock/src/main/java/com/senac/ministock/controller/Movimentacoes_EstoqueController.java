package com.senac.ministock.controller;

import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTORequest;
import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTOUpdateRequest;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOResponse;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOUpdateResponse;
import com.senac.ministock.entity.Movimentacoes_Estoque;
import com.senac.ministock.service.Movimentacoes_EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimentacoes-estoque")
public class Movimentacoes_EstoqueController {
    private final Movimentacoes_EstoqueService movimentacoesEstoqueService;

    public Movimentacoes_EstoqueController(Movimentacoes_EstoqueService movimentacoesEstoqueService) {
        this.movimentacoesEstoqueService = movimentacoesEstoqueService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Movimentacoes_Estoque>> listarMovimentacoesEstoque() {
        return ResponseEntity.ok(movimentacoesEstoqueService.listarMovimentacoesEstoque());
    }

    @GetMapping("/listarPorId/{movimentacaoId}")
    @Operation(summary = "Listar movimentação pelo id", description = "Endpoint para buscar movimentação pelo id")
    public ResponseEntity<Movimentacoes_Estoque> listarPorId(@PathVariable("movimentacaoId") Integer movimentacaoId) {
        Movimentacoes_Estoque movimentacao = movimentacoesEstoqueService.listarPorId(movimentacaoId);
        if (movimentacao == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(movimentacao);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova movimentação", description = "Endpoint para criar uma nova movimentação")
    public ResponseEntity<Movimentacoes_EstoqueDTOResponse> criarMovimentacao(@Valid @RequestBody Movimentacoes_EstoqueDTORequest movimentacaoDTORequest) {
        Movimentacoes_EstoqueDTOResponse response = movimentacoesEstoqueService.criarMovimentacao(movimentacaoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{movimentacaoId}")
    @Operation(summary = "Atualizar todos os dados da movimentação", description = "Endpoint para atualizar todos os registros de movimentação")
    public ResponseEntity<Object> atualizarMovimentacao(
            @PathVariable("movimentacaoId") Integer movimentacaoId,
            @RequestBody Movimentacoes_EstoqueDTORequest movimentacaoDTORequest) {
        return ResponseEntity.ok(movimentacoesEstoqueService.atualizarMovimentacao(movimentacaoId, movimentacaoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{movimentacaoId}")
    @Operation(summary = "Atualizar campo de status da movimentação", description = "Endpoint para atualizar o status da movimentação")
    public ResponseEntity<Movimentacoes_EstoqueDTOUpdateResponse> atualizarStatusMovimentacao(
            @PathVariable("movimentacaoId") Integer movimentacaoId,
            @RequestBody Movimentacoes_EstoqueDTOUpdateRequest movimentacaoDTOUpdateRequest) {
        Movimentacoes_EstoqueDTOUpdateResponse response = movimentacoesEstoqueService.atualizarStatusMovimentacao(movimentacaoId, movimentacaoDTOUpdateRequest);
        return ResponseEntity.ok(response);
    }
}