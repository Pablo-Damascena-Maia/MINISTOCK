package com.senac.ministock.repository;

import com.senac.ministock.entity.Movimentacoes_Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Movimentacao_EstoqueRepository extends JpaRepository<Movimentacoes_Estoque,Integer> {
}
