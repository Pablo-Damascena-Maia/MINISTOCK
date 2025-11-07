package com.senac.ministock.repository;

import com.senac.ministock.entity.MovimentacoesEstoque;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacoesEstoqueRepository extends JpaRepository<MovimentacoesEstoque, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE MovimentacoesEstoque m SET m.status = -1 WHERE m.id = :id")
    void apagadoLogicoMovimentacao(@Param("id") Integer id);

    @Query("SELECT m FROM MovimentacoesEstoque m WHERE m.status >= 0")
    List<MovimentacoesEstoque> listarMovimentacoes();

    @Query("SELECT m FROM MovimentacoesEstoque m WHERE m.id = :id AND m.status >= 0")
    MovimentacoesEstoque obterMovimentacaoPeloId(@Param("id") Integer id);
}
