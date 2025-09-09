package com.senac.ministock.controller;

import com.senac.ministock.dto.request.ConfiguracaoDTORequest;
import com.senac.ministock.dto.response.ConfiguracaoDTOResponse;
import com.senac.ministock.dto.response.ConfiguracaoDTOUpdateResponse;
import com.senac.ministock.service.ConfiguracaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/configuracao")
public class ConfiguracaoController {

    private final ConfiguracaoService configuracaoService;

    public ConfiguracaoController(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar configurações",
            description = "Endpoint para listar todas as configurações"
    )
    public ResponseEntity<List<ConfiguracaoDTOResponse>> listarConfiguracoes() {
        return ResponseEntity.ok(configuracaoService.listarConfiguracoes());
    }

    @GetMapping("/listarPorConfiguracaoId/{configuracaoId}")
    @Operation(
            summary = "Listar configuração pelo id",
            description = "Endpoint para listar configuração por Id"
    )
    public ResponseEntity<ConfiguracaoDTOResponse> listarPorConfiguracaoId(@PathVariable("configuracaoId") Integer configuracaoId) {
        ConfiguracaoDTOResponse configuracao = configuracaoService.listarPorConfiguracaoId(configuracaoId);
        if (configuracao == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(configuracao);
        }
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Criar nova configuração",
            description = "Endpoint para criar um novo registro de configuração"
    )
    public ResponseEntity<ConfiguracaoDTOResponse> criarConfiguracao(
            @Valid @RequestBody ConfiguracaoDTORequest configuracao
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(configuracaoService.criarConfiguracao(configuracao));
    }

    @PutMapping("/atualizar/{configuracaoId}")
    @Operation(
            summary = "Atualizar todos os dados da configuração",
            description = "Endpoint para atualizar o registro de configuração"
    )
    public ResponseEntity<ConfiguracaoDTOResponse> atualizarConfiguracao(
            @PathVariable("configuracaoId") Integer configuracaoId,
            @Valid @RequestBody ConfiguracaoDTORequest configuracaoDTORequest
    ) {
        return ResponseEntity.ok(configuracaoService.atualizarConfiguracao(configuracaoId, configuracaoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{configuracaoId}")
    @Operation(
            summary = "Atualizar campo status da configuração",
            description = "Endpoint para atualizar apenas o status da configuração"
    )
    public ResponseEntity<ConfiguracaoDTOUpdateResponse> atualizarStatusConfiguracao(
            @PathVariable("configuracaoId") Integer configuracaoId,
            @Valid @RequestBody ConfiguracaoDTORequest configuracaoDTOUpdateRequest
    ) {
        return ResponseEntity.ok(configuracaoService.atualizarStatusConfiguracao(configuracaoId, configuracaoDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{configuracaoId}")
    @Operation(
            summary = "Apagar registro da configuração",
            description = "Endpoint para apagar registro da configuração"
    )
    public ResponseEntity<Void> apagarConfiguracao(@PathVariable("configuracaoId") Integer configuracaoId) {
        configuracaoService.apagarConfiguracao(configuracaoId);
        return ResponseEntity.noContent().build();
    }
}
