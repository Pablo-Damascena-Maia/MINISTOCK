package com.senac.ministock.controller;

import com.senac.ministock.entity.Configuracao;
import com.senac.ministock.service.ConfiguracaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/configuracao")
public class ConfiguracaoController {
    private ConfiguracaoService configuracaoService;

    public ConfiguracaoController(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Configuracao>>listarConfiguracao(){
        return ResponseEntity.ok(configuracaoService.listarConfiguracao());
    }
}
