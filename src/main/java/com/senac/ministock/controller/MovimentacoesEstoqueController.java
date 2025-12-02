package com.senac.ministock.controller;

import com.senac.ministock.dto.request.MovimentacoesEstoqueDTORequest;
import com.senac.ministock.dto.response.MovimentacoesEstoqueDTOResponse;
import com.senac.ministock.service.MovimentacoesEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimentacoes_estoque")
@CrossOrigin("*")
public class MovimentacoesEstoqueController {

    private final MovimentacoesEstoqueService movimentacoes_EstoqueService;

    public MovimentacoesEstoqueController(MovimentacoesEstoqueService movimentacoes_EstoqueService) {
        this.movimentacoes_EstoqueService = movimentacoes_EstoqueService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as movimentações", description = "Retorna a lista completa de movimentações")
    public ResponseEntity<List<MovimentacoesEstoqueDTOResponse>> listarMovimentacoes() {
        return ResponseEntity.ok(movimentacoes_EstoqueService.listarMovimentacoes());
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova movimentação", description = "Cria um registro de movimentação de estoque")
    public ResponseEntity<MovimentacoesEstoqueDTOResponse> criarMovimentacao(
            @Valid @RequestBody MovimentacoesEstoqueDTORequest dto
    ) {
        MovimentacoesEstoqueDTOResponse response = movimentacoes_EstoqueService.criarMovimentacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar movimentação", description = "Atualiza um registro de movimentação existente")
    public ResponseEntity<MovimentacoesEstoqueDTOResponse> atualizarMovimentacao(
            @PathVariable("id") int id,
            @Valid @RequestBody MovimentacoesEstoqueDTORequest dto
    ) {
        MovimentacoesEstoqueDTOResponse response = movimentacoes_EstoqueService.atualizarMovimentacao(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remover/{id}")
    @Operation(summary = "Remover movimentação", description = "Remove uma movimentação de estoque pelo ID")
    public ResponseEntity<Void> removerMovimentacao(@PathVariable("id") int id) {
        movimentacoes_EstoqueService.apagarMovimentacao(id);
        return ResponseEntity.noContent().build();
    }
}
