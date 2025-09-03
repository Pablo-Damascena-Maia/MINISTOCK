package com.senac.ministock.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.senac.ministock.dto.request.ProdutoDTORequest;
import com.senac.ministock.dto.request.ProdutoDTOUpdateRequest;
import com.senac.ministock.dto.response.ProdutoDTOResponse;
import com.senac.ministock.entity.Produto;
import com.senac.ministock.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/listarPorId/{produtoId}")
    @Operation(summary = "Listar produto pelo id", description = "Endpoint para buscar produto pelo id")
    public ResponseEntity<Produto> listarPorId(@PathVariable("produtoId") Integer produtoId) {
        Produto produto = produtoService.listarPorId(produtoId);
        if (produto == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(produto);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo produto", description = "Endpoint para criar um novo produto")
    public ResponseEntity<ProdutoDTOResponse> criarProduto(@Valid @RequestBody ProdutoDTORequest produtoDTORequest) {
        ProdutoDTOResponse response = (ProdutoDTOResponse) produtoService.criarProduto(produtoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{produtoId}")
    @Operation(summary = "Atualizar todos os dados do produto", description = "Endpoint para atualizar todos os registros do produto")
    public ResponseEntity<Object> atualizarProduto(
            @PathVariable("produtoId") Integer produtoId,
            @RequestBody ProdutoDTOUpdateRequest produtoDTOUpdateRequest) {
        return ResponseEntity.ok(produtoService.atualizarProduto(produtoId, produtoDTOUpdateRequest));
    }

    @PatchMapping("/atualizarStatus/{produtoId}")
    @Operation(summary = "Atualizar campo de status do produto", description = "Endpoint para atualizar apenas o status do produto")
    public ResponseEntity<Object> atualizarStatusProduto(
            @PathVariable("produtoId") Integer produtoId,
            @RequestBody ProdutoDTOUpdateRequest produtoDTOUpdateRequest) {
        return ResponseEntity.ok(produtoService.atualizarStatusProduto(produtoId, produtoDTOUpdateRequest));
    }
}
