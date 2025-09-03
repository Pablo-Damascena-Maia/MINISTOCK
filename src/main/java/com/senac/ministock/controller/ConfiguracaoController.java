package com.senac.ministock.controller;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.senac.ministock.dto.request.ConfiguracaoDTORequest;
import com.senac.ministock.dto.request.ConfiguracaoDTOUpdateRequest;
import com.senac.ministock.dto.response.ConfiguracaoDTOResponse;
import com.senac.ministock.entity.Configuracao;
import com.senac.ministock.service.ConfiguracaoService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("api/configuracao")
public class ConfiguracaoController {
    private ConfiguracaoService configuracaoService;

    public ConfiguracaoController(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<com.senac.ministock.entity.Configuracao>> listarConfiguracao() {
        return ResponseEntity.ok(configuracaoService.listarConfiguracao());
    }

    @GetMapping("/listarPorId/{configuracaoId}")
    @Operation(summary = "Listar configuração pelo id", description = "Endpoint para buscar configuração pelo id")
    public ResponseEntity<Configuracao> listarPorId(@PathVariable("configuracaoId") Integer configuracaoId) {
        Configuracao configuracao = configuracaoService.listarPorId(configuracaoId);
        if (configuracao == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(configuracao);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova configuração", description = "Endpoint para criar uma nova configuração")
    public ResponseEntity<ConfiguracaoDTOResponse> criarConfiguracao(@Valid @RequestBody ConfiguracaoDTORequest configuracaoDTORequest) {
        ConfiguracaoDTOResponse response = (ConfiguracaoDTOResponse) configuracaoService.criarConfiguracao(configuracaoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{configuracaoId}")
    @Operation(summary = "Atualizar todos os dados da configuração", description = "Endpoint para atualizar todos os registros de configuração")
    public ResponseEntity<Object> atualizarConfiguracao(
            @PathVariable("configuracaoId") Integer configuracaoId,
            @RequestBody ConfiguracaoDTOUpdateRequest configuracaoDTOUpdateRequest) {
        return ResponseEntity.ok(configuracaoService.atualizarStatusConfiguracao(configuracaoId, configuracaoDTOUpdateRequest));
    }

    @PatchMapping("/atualizarStatus/{configuracaoId}")
    @Operation(summary = "Atualizar campo de status da configuração", description = "Endpoint para atualizar o status da configuração")
    public ResponseEntity<Object> atualizarStatusConfiguracao(
            @PathVariable("configuracaoId") Integer configuracaoId,
            @RequestBody ConfiguracaoDTOUpdateRequest configuracaoDTOUpdateRequest) {
        return ResponseEntity.ok(configuracaoService.atualizarStatusConfiguracao(configuracaoId, configuracaoDTOUpdateRequest));
    }
}
