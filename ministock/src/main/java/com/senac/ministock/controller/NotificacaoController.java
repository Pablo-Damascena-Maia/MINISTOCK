package com.senac.ministock.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.senac.ministock.dto.request.NotificacaoDTORequest;
import com.senac.ministock.dto.request.NotificacaoDTOUpdateRequest;
import com.senac.ministock.dto.response.NotificacaoDTOResponse;
import com.senac.ministock.entity.Notificacao;
import com.senac.ministock.service.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("api/notificacao")
public class NotificacaoController {
    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Notificacao>> listarNotificacoes() {
        return ResponseEntity.ok(notificacaoService.listarNotificacoes());
    }

    @GetMapping("/listarPorId/{notificacaoId}")
    @Operation(summary = "Listar notificação pelo id", description = "Endpoint para buscar notificação pelo id")
    public ResponseEntity<Notificacao> listarPorId(@PathVariable("notificacaoId") Integer notificacaoId) {
        Notificacao notificacao = notificacaoService.listarPorId(notificacaoId);
        if (notificacao == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notificacao);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar nova notificação", description = "Endpoint para criar uma nova notificação")
    public ResponseEntity<NotificacaoDTOResponse> criarNotificacao(@Valid @RequestBody NotificacaoDTORequest notificacaoDTORequest) {
        NotificacaoDTOResponse response = (NotificacaoDTOResponse) notificacaoService.criarNotificacao(notificacaoDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{notificacaoId}")
    @Operation(summary = "Atualizar todos os dados da notificação", description = "Endpoint para atualizar todos os registros da notificação")
    public ResponseEntity<Object> atualizarNotificacao(
            @PathVariable("notificacaoId") Integer notificacaoId,
            @RequestBody NotificacaoDTOUpdateRequest notificacaoDTOUpdateRequest) {
        return ResponseEntity.ok(notificacaoService.atualizarNotificacao(notificacaoId, notificacaoDTOUpdateRequest));
    }

    @PatchMapping("/atualizarStatus/{notificacaoId}")
    @Operation(summary = "Atualizar campo de status da notificação", description = "Endpoint para atualizar apenas o status da notificação")
    public ResponseEntity<Object> atualizarStatusNotificacao(
            @PathVariable("notificacaoId") Integer notificacaoId,
            @RequestBody NotificacaoDTOUpdateRequest notificacaoDTOUpdateRequest) {
        return ResponseEntity.ok(notificacaoService.atualizarStatusNotificacao(notificacaoId, notificacaoDTOUpdateRequest));
    }
}
