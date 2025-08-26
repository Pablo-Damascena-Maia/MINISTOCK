package com.senac.ministock.repository;

import com.senac.ministock.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Integer> {
}
