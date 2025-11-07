package com.senac.ministock.repository;

import com.senac.ministock.entity.CategoriaProduto;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoriaProdutoReposytory extends JpaRepository<CategoriaProduto, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Categoria_Produto cp SET cp.status = -1 WHERE cp.id = :id")
    void apagadoLogicoCategoriaProduto(@Param("id") Integer id);
    @Query("SELECT cp FROM Categoria_Produto cp WHERE cp.status >= 0")
    List<CategoriaProduto> listarCategoriaProduto();

    // Obter notificação pelo id, apenas se ativa
    @Query("SELECT cp FROM Categoria_Produto cp WHERE c.id = :id AND cp.status >= 0")
    CategoriaProduto obterCategoriaProdutoPeloId(@Param("id") Integer id);
}
