package com.senac.ministock.service;

import com.senac.ministock.repository.NotificacaoRepository;
import com.senac.ministock.entity.Notificacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {
    private NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }
    public List<Notificacao> listarNotificacao(){
        return this.notificacaoRepository.findAll();
    }

}
