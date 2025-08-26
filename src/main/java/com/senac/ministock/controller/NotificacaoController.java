package com.senac.ministock.controller;

import com.senac.ministock.entity.Notificacao;
import com.senac.ministock.service.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/notidicacao")
public class NotificacaoController {
    private NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Notificacao>>listarnotificacao(){
        return new ResponseEntity.ok(notificacaoService.listarNotificacao());
    }
}
