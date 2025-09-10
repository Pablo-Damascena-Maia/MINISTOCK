package com.senac.ministock.controller;

import com.senac.ministock.dto.request.NotificacaoDTORequest;
import com.senac.ministock.dto.response.NotificacaoDTOResponse;
import com.senac.ministock.dto.response.NotificacaoDTOUpdateResponse;
import com.senac.ministock.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notificacao")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar notificações",
            description = "Endpoint para listar todas as notificações"
    )
    public ResponseEntity<List<NotificacaoDTOResponse>> listarNotificacoes() {
        return ResponseEntity.ok(notificacaoService.listarNotificacoes());
    }

    @GetMapping("/listarPorNotificacaoId/{notificacaoId}")
    @Operation(
            summary = "Listar notificação pelo id",
            description = "Endpoint para listar notificação por Id"
    )
    public ResponseEntity<NotificacaoDTOResponse> listarPorNotificacaoId(@PathVariable("notificacaoId") Integer notificacaoId) {
        NotificacaoDTOResponse notificacao = notificacaoService.listarPorNotificacaoId(notificacaoId);
        if (notificacao == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notificacao);
        }
    }

    @PostMapping("/criar")
    @Operation(
            summary = "Criar nova notificação",
            description = "Endpoint para criar um novo registro de notificação"
    )
    public ResponseEntity<NotificacaoDTOResponse> criarNotificacao(
            @Valid @RequestBody NotificacaoDTORequest notificacao
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoService.criarNotificacao(notificacao));
    }

    @PutMapping("/atualizar/{notificacaoId}")
    @Operation(
            summary = "Atualizar todos os dados da notificação",
            description = "Endpoint para atualizar o registro de notificação"
    )
    public ResponseEntity<NotificacaoDTOResponse> atualizarNotificacao(
            @PathVariable("notificacaoId") Integer notificacaoId,
            @Valid @RequestBody NotificacaoDTORequest notificacaoDTORequest
    ) {
        return ResponseEntity.ok(notificacaoService.atualizarNotificacao(notificacaoId, notificacaoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{notificacaoId}")
    @Operation(
            summary = "Atualizar campo status da notificação",
            description = "Endpoint para atualizar apenas o status da notificação"
    )
    public ResponseEntity<NotificacaoDTOUpdateResponse> atualizarStatusNotificacao(
            @PathVariable("notificacaoId") Integer notificacaoId,
            @Valid @RequestBody NotificacaoDTORequest notificacaoDTOUpdateRequest
    ) {
        return ResponseEntity.ok(notificacaoService.atualizarStatusNotificacao(notificacaoId, notificacaoDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{notificacaoId}")
    @Operation(
            summary = "Apagar registro da notificação",
            description = "Endpoint para apagar registro da notificação"
    )
    public ResponseEntity<Void> apagarNotificacao(@PathVariable("notificacaoId") Integer notificacaoId) {
        notificacaoService.apagarNotificacao(notificacaoId);
        return ResponseEntity.noContent().build();
    }
}
