package com.senac.ministock.controller;

import com.senac.ministock.dto.request.CategoriaProdutoRequest;
import com.senac.ministock.dto.response.CategoriaProdutoResponse;
import com.senac.ministock.dto.response.ProdutoDTOUpdateResponse;
import com.senac.ministock.service.CategoriaProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/categoriaProduto")
@CrossOrigin("*")
public class CategoriaController {
    private final CategoriaProdutoService categoriaProdutoService;

    public CategoriaController(CategoriaProdutoService categoriaProdutoService) {
        this.categoriaProdutoService = categoriaProdutoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar produtos", description = "Endpoint para listar todos os produtos")
    public ResponseEntity<List<CategoriaProdutoResponse>> listarCategoriaProdutos() {
        return ResponseEntity.ok(categoriaProdutoService.listarProdutos());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar produto por ID", description = "Endpoint para listar produto por ID")
    public ResponseEntity<CategoriaProdutoResponse> listarPorId(@PathVariable("id") Integer id) {
        CategoriaProdutoResponse dto = categoriaProdutoService.listarPorId(id);
        if (dto == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar produto", description = "Endpoint para criar um novo produto")
    public ResponseEntity<CategoriaProdutoResponse> criarCategoriaProduto(@Valid @RequestBody CategoriaProdutoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaProdutoService.criarProduto(dto));
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar produto", description = "Endpoint para atualizar todos os dados do produto")
    public ResponseEntity<CategoriaProdutoResponse> atualizarCategoriaProduto(
            @PathVariable("id") Integer id,
            @Valid @RequestBody CategoriaProdutoRequest dto) {
        return ResponseEntity.ok(categoriaProdutoService.atualizarProduto(id, dto));
    }

    @PatchMapping("/atualizarStatus/{id}")
    @Operation(summary = "Atualizar status do produto", description = "Endpoint para atualizar apenas o status")
    public ResponseEntity<ProdutoDTOUpdateResponse> atualizarStatus(
            @PathVariable("id") Integer id,
            @Valid @RequestBody CategoriaProdutoRequest dto) {
        return ResponseEntity.ok(categoriaProdutoService.atualizarStatusProduto(id, dto));
    }

    @DeleteMapping("/apagar/{id}")
    @Operation(summary = "Apagar produto", description = "Endpoint para apagar produto")
    public ResponseEntity<Void> apagar(@PathVariable("id") Integer id) {
        categoriaProdutoService.apagarCategoriaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
