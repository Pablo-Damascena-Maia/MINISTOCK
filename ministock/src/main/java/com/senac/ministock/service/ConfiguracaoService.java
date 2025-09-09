package com.senac.ministock.service;

import com.senac.ministock.dto.request.ConfiguracaoDTORequest;
import com.senac.ministock.dto.response.ConfiguracaoDTOResponse;
import com.senac.ministock.dto.response.ConfiguracaoDTOUpdateResponse;
import com.senac.ministock.entity.Configuracao;
import com.senac.ministock.repository.ConfiguracaoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConfiguracaoService {

    private final ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public ConfiguracaoService(ConfiguracaoRepository configuracaoRepository,
                               ModelMapper modelMapper) {
        this.configuracaoRepository = configuracaoRepository;
        this.modelMapper = modelMapper;
    }

    public List<ConfiguracaoDTOResponse> listarConfiguracoes() {
        return configuracaoRepository.listarConfiguracoes()
                .stream()
                .map(configuracao -> modelMapper.map(configuracao, ConfiguracaoDTOResponse.class))
                .toList();
    }

    public ConfiguracaoDTOResponse listarPorConfiguracaoId(Integer configuracaoId) {
        Configuracao configuracao = configuracaoRepository.obterConfiguracaoPeloId(configuracaoId);
        return (configuracao != null) ? modelMapper.map(configuracao, ConfiguracaoDTOResponse.class) : null;
    }

    @Transactional
    public ConfiguracaoDTOResponse criarConfiguracao(ConfiguracaoDTORequest configuracaoDTORequest) {
        Configuracao configuracao = modelMapper.map(configuracaoDTORequest, Configuracao.class);
        Configuracao configuracaoSave = configuracaoRepository.save(configuracao);
        return modelMapper.map(configuracaoSave, ConfiguracaoDTOResponse.class);
    }

    @Transactional
    public ConfiguracaoDTOResponse atualizarConfiguracao(Integer configuracaoId, ConfiguracaoDTORequest configuracaoDTORequest) {
        Configuracao configuracao = configuracaoRepository.obterConfiguracaoPeloId(configuracaoId);
        if (configuracao != null) {
            modelMapper.map(configuracaoDTORequest, configuracao);
            Configuracao tempResponse = configuracaoRepository.save(configuracao);
            return modelMapper.map(tempResponse, ConfiguracaoDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ConfiguracaoDTOUpdateResponse atualizarStatusConfiguracao(Integer configuracaoId, ConfiguracaoDTORequest configuracaoDTOUpdateRequest) {
        Configuracao configuracao = configuracaoRepository.obterConfiguracaoPeloId(configuracaoId);
        if (configuracao != null) {
            configuracao.setStatus(configuracaoDTOUpdateRequest.getStatus());
            Configuracao configuracaoSave = configuracaoRepository.save(configuracao);
            return modelMapper.map(configuracaoSave, ConfiguracaoDTOUpdateResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarConfiguracao(Integer configuracaoId) {
        configuracaoRepository.apagadoLogicoConfiguracao(configuracaoId);
    }
}
