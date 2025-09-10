package com.senac.ministock.service;

import com.senac.ministock.dto.request.NotificacaoDTORequest;
import com.senac.ministock.dto.response.NotificacaoDTOResponse;
import com.senac.ministock.dto.response.NotificacaoDTOUpdateResponse;
import com.senac.ministock.entity.Notificacao;
import com.senac.ministock.repository.NotificacaoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final ModelMapper modelMapper;

    public NotificacaoService(NotificacaoRepository notificacaoRepository,
                              ModelMapper modelMapper) {
        this.notificacaoRepository = notificacaoRepository;
        this.modelMapper = modelMapper;
    }

    public List<NotificacaoDTOResponse> listarNotificacoes() {
        return notificacaoRepository.findAll()
                .stream()
                .map(notificacao -> modelMapper.map(notificacao, NotificacaoDTOResponse.class))
                .toList();
    }

    public NotificacaoDTOResponse listarPorNotificacaoId(Integer notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId).orElse(null);
        return (notificacao != null) ? modelMapper.map(notificacao, NotificacaoDTOResponse.class) : null;
    }

    @Transactional
    public NotificacaoDTOResponse criarNotificacao(NotificacaoDTORequest notificacaoDTORequest) {
        Notificacao notificacao = modelMapper.map(notificacaoDTORequest, Notificacao.class);
        notificacao.setVersion(0); // inicializa version
        Notificacao notificacaoSalva = notificacaoRepository.save(notificacao);
        return modelMapper.map(notificacaoSalva, NotificacaoDTOResponse.class);
    }

    @Transactional
    public NotificacaoDTOResponse atualizarNotificacao(Integer notificacaoId, NotificacaoDTORequest notificacaoDTORequest) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Notificação não encontrada"));
        modelMapper.map(notificacaoDTORequest, notificacao);
        Notificacao notificacaoAtualizada = notificacaoRepository.save(notificacao);
        return modelMapper.map(notificacaoAtualizada, NotificacaoDTOResponse.class);
    }

    @Transactional
    public NotificacaoDTOUpdateResponse atualizarStatusNotificacao(Integer notificacaoId, NotificacaoDTORequest notificacaoDTORequest) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Notificação não encontrada"));
        notificacao.setStatus(notificacaoDTORequest.getStatus());
        Notificacao notificacaoAtualizada = notificacaoRepository.save(notificacao);
        return modelMapper.map(notificacaoAtualizada, NotificacaoDTOUpdateResponse.class);
    }

    @Transactional
    public void apagarNotificacao(Integer notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Notificação não encontrada"));
        notificacao.setStatus(0); // apagado lógico
        notificacaoRepository.save(notificacao);
    }
}
