package com.senac.ministock.service;

import com.senac.ministock.repository.NotificacaoRepository;
import com.senac.ministock.dto.request.NotificacaoDTORequest;
import com.senac.ministock.dto.request.NotificacaoDTOUpdateRequest;
import com.senac.ministock.dto.response.NotificacaoDTOResponse;
import com.senac.ministock.dto.response.NotificacaoDTOUpdateResponse;
import com.senac.ministock.entity.Notificacao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public List<Notificacao> listarNotificacoes() {
        return notificacaoRepository.findAll();
    }

    public Notificacao listarPorId(Integer notificacaoId) {
        Optional<Notificacao> notificacao = notificacaoRepository.findById(notificacaoId);
        return notificacao.orElse(null);
    }

    public NotificacaoDTOResponse criarNotificacao(NotificacaoDTORequest notificacaoDTORequest) {
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo(notificacaoDTORequest.getTitulo());
        notificacao.setMensagem(notificacaoDTORequest.getMensagem());
        notificacao.setStatus(notificacaoDTORequest.getStatus());
        // Adicione outros campos conforme necessário

        Notificacao saved = notificacaoRepository.save(notificacao);

        NotificacaoDTOResponse response = new NotificacaoDTOResponse();
        response.setId(saved.getId());
        response.setTitulo(saved.getTitulo());
        response.setMensagem(saved.getMensagem());
        response.setStatus(saved.getStatus());
        // Adicione outros campos conforme necessário

        return response;
    }

    public NotificacaoDTOResponse atualizarNotificacao(Integer notificacaoId, NotificacaoDTOUpdateRequest notificacaoDTOUpdateRequest) {
        Optional<Notificacao> notificacaoOpt = notificacaoRepository.findById(notificacaoId);
        if (notificacaoOpt.isPresent()) {
            Notificacao notificacao = notificacaoOpt.get();
            notificacao.setTitulo(notificacaoDTOUpdateRequest.getTitulo());
            notificacao.setMensagem(notificacaoDTOUpdateRequest.getMensagem());
            notificacao.setStatus(notificacaoDTOUpdateRequest.getStatus());
            // Atualize outros campos conforme necessário

            Notificacao updated = notificacaoRepository.save(notificacao);

            NotificacaoDTOResponse response = new NotificacaoDTOResponse();
            response.setId(updated.getId());
            response.setTitulo(updated.getTitulo());
            response.setMensagem(updated.getMensagem());
            response.setStatus(updated.getStatus());
            // Adicione outros campos conforme necessário

            return response;
        }
        return null;
    }

    public NotificacaoDTOUpdateResponse atualizarStatusNotificacao(Integer notificacaoId, NotificacaoDTOUpdateRequest notificacaoDTOUpdateRequest) {
        Optional<Notificacao> notificacaoOpt = notificacaoRepository.findById(notificacaoId);
        if (notificacaoOpt.isPresent()) {
            Notificacao notificacao = notificacaoOpt.get();
            notificacao.setStatus(notificacaoDTOUpdateRequest.getStatus());

            Notificacao updated = notificacaoRepository.save(notificacao);

            NotificacaoDTOUpdateResponse response = new NotificacaoDTOUpdateResponse();
            response.setId(updated.getId());
            response.setStatus(updated.getStatus());

            return response;
        }
        return null;
    }
}
