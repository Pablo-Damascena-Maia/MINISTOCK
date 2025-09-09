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
    @Operation(summary = "Listar notificações", description = "Endpoint para listar todas as notificações")
    public ResponseEntity<List<NotificacaoDTOResponse>> listarNotificacoes() {
        return ResponseEntity.ok(notificacaoService.listarNotificacoes());
    }

    @GetMapping("/listarPorId/{id}")
    @Operation(summary = "Listar notificação por ID", description = "Endpoint para listar notificação por ID")
    public ResponseEntity<NotificacaoDTOResponse> listarPorId(@PathVariable("id") Integer id) {
        NotificacaoDTOResponse dto = notificacaoService.listarPorId(id);
        if (dto == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar notificação", description = "Endpoint para criar uma nova notificação")
    public ResponseEntity<NotificacaoDTOResponse> criarNotificacao(
            @Valid @RequestBody NotificacaoDTORequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacaoService.criarNotificacao(dto));
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar notificação", description = "Endpoint para atualizar todos os dados da notificação")
    public ResponseEntity<NotificacaoDTOResponse> atualizarNotificacao(
            @PathVariable("id") Integer id,
            @Valid @RequestBody NotificacaoDTORequest dto) {
        return ResponseEntity.ok(notificacaoService.atualizarNotificacao(id, dto));
    }

    @PatchMapping("/atualizarStatus/{id}")
    @Operation(summary = "Atualizar status da notificação", description = "Endpoint para atualizar apenas o status")
    public ResponseEntity<NotificacaoDTOUpdateResponse> atualizarStatus(
            @PathVariable("id") Integer id,
            @Valid @RequestBody NotificacaoDTORequest dto) {
        return ResponseEntity.ok(notificacaoService.atualizarStatusNotificacao(id, dto));
    }

    @DeleteMapping("/apagar/{id}")
    @Operation(summary = "Apagar notificação", description = "Endpoint para apagar notificação")
    public ResponseEntity<Void> apagar(@PathVariable("id") Integer id) {
        notificacaoService.apagarNotificacao(id);
        return ResponseEntity.noContent().build();
    }
}
