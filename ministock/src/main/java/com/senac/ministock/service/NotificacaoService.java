package com.senac.ministock.service;

import com.senac.ministock.dto.request.NotificacaoDTORequest;
import com.senac.ministock.dto.response.NotificacaoDTOResponse;
import com.senac.ministock.dto.response.NotificacaoDTOUpdateResponse;
import com.senac.ministock.entity.Notificacao;
import com.senac.ministock.repository.NotificacaoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public NotificacaoService(NotificacaoRepository notificacaoRepository,
                              ModelMapper modelMapper) {
        this.notificacaoRepository = notificacaoRepository;
        this.modelMapper = modelMapper;
    }

    public List<NotificacaoDTOResponse> listarNotificacoes() {
        return notificacaoRepository.listarNotificacoes()
                .stream()
                .map(n -> modelMapper.map(n, NotificacaoDTOResponse.class))
                .toList();
    }

    public NotificacaoDTOResponse listarPorId(Integer id) {
        Notificacao n = notificacaoRepository.obterNotificacaoPeloId(id);
        return (n != null) ? modelMapper.map(n, NotificacaoDTOResponse.class) : null;
    }

    @Transactional
    public NotificacaoDTOResponse criarNotificacao(NotificacaoDTORequest dto) {
        Notificacao n = modelMapper.map(dto, Notificacao.class);
        Notificacao salvo = notificacaoRepository.save(n);
        return modelMapper.map(salvo, NotificacaoDTOResponse.class);
    }

    @Transactional
    public NotificacaoDTOResponse atualizarNotificacao(Integer id, NotificacaoDTORequest dto) {
        Notificacao n = notificacaoRepository.obterNotificacaoPeloId(id);
        if (n != null) {
            modelMapper.map(dto, n);
            Notificacao salvo = notificacaoRepository.save(n);
            return modelMapper.map(salvo, NotificacaoDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public NotificacaoDTOUpdateResponse atualizarStatusNotificacao(Integer id, NotificacaoDTORequest dto) {
        Notificacao n = notificacaoRepository.obterNotificacaoPeloId(id);
        if (n != null) {
            n.setStatus(dto.getStatus());
            Notificacao salvo = notificacaoRepository.save(n);
            return modelMapper.map(salvo, NotificacaoDTOUpdateResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarNotificacao(Integer id) {
        notificacaoRepository.apagadoLogicoNotificacao(id);
    }
}
