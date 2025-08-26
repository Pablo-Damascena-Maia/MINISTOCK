package com.senac.ministock.service;

import com.senac.ministock.repository.ConfiguracaoRepository;
import com.senac.ministock.entity.Configuracao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracaoService {
    private ConfiguracaoRepository configuracaoRepository;

    public ConfiguracaoService(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }
    public List<Configuracao>listarConfiguracao(){
        return this.configuracaoRepository.findAll();
    }
}
