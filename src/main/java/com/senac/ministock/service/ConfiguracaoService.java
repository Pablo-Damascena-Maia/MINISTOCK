package com.senac.ministock.service;

import com.senac.ministock.repository.ConfiguracaoRepository;
import com.senac.ministock.dto.request.ConfiguracaoDTORequest;
import com.senac.ministock.dto.request.ConfiguracaoDTOUpdateRequest;
import com.senac.ministock.dto.response.ConfiguracaoDTOResponse;
import com.senac.ministock.dto.response.ConfiguracaoDTOUpdateResponse;
import com.senac.ministock.entity.Configuracao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoService {
    private final ConfiguracaoRepository configuracaoRepository;

    public ConfiguracaoService(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }

    public List<Configuracao> listarConfiguracao() {
        return configuracaoRepository.findAll();
    }

    public Configuracao listarPorId(Integer configuracaoId) {
        Optional<Configuracao> configuracao = configuracaoRepository.findById(configuracaoId);
        return configuracao.orElse(null);
    }

    public ConfiguracaoDTOResponse criarConfiguracao(ConfiguracaoDTORequest configuracaoDTORequest) {
        Configuracao configuracao = new Configuracao();
        configuracao.setChave(configuracaoDTORequest.getChave());
        configuracao.setStatus(configuracaoDTORequest.getStatus());
        // Adicione outros campos conforme necessário

        Configuracao saved = configuracaoRepository.save(configuracao);

        ConfiguracaoDTOResponse response = new ConfiguracaoDTOResponse();
        response.setId(saved.getId());
        response.setChave(saved.getChave());
        response.setStatus(saved.getStatus());
        // Adicione outros campos conforme necessário

        return response;
    }

    public ConfiguracaoDTOResponse atualizarConfiguracao(Integer configuracaoId, ConfiguracaoDTOUpdateRequest configuracaoDTOUpdateRequest) {
        Optional<Configuracao> configuracaoOpt = configuracaoRepository.findById(configuracaoId);
        if (configuracaoOpt.isPresent()) {
            Configuracao configuracao = configuracaoOpt.get();
            configuracao.setChave(configuracaoDTOUpdateRequest.getChave());
            configuracao.setStatus(configuracaoDTOUpdateRequest.getStatus());
            // Atualize outros campos conforme necessário

            Configuracao updated = configuracaoRepository.save(configuracao);

            ConfiguracaoDTOResponse response = new ConfiguracaoDTOResponse();
            response.setId(updated.getId());
            response.setChave(updated.getChave());
            response.setStatus(updated.getStatus());
            // Adicione outros campos conforme necessário

            return response;
        }
        return null;
    }

    public ConfiguracaoDTOUpdateResponse atualizarStatusConfiguracao(Integer configuracaoId, ConfiguracaoDTOUpdateRequest configuracaoDTOUpdateRequest) {
        Optional<Configuracao> configuracaoOpt = configuracaoRepository.findById(configuracaoId);
        if (configuracaoOpt.isPresent()) {
            Configuracao configuracao = configuracaoOpt.get();
            configuracao.setStatus(configuracaoDTOUpdateRequest.getStatus());

            Configuracao updated = configuracaoRepository.save(configuracao);

            ConfiguracaoDTOUpdateResponse response = new ConfiguracaoDTOUpdateResponse();
            response.setId(updated.getId());
            response.setStatus(updated.getStatus());

            return response;
        }
        return null;
    }
}
