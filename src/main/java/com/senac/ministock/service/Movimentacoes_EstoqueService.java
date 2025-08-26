package com.senac.ministock.service;

import com.senac.ministock.repository.Movimentacoes_EstoqueRepository;
import com.senac.ministock.entity.Movimentacoes_Estoque;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Movimentacoes_EstoqueService {
    private Movimentacoes_EstoqueRepository movimentacoesEstoqueRepository;

    public Movimentacoes_EstoqueService(Movimentacoes_EstoqueRepository movimentacoesEstoqueRepository) {
        this.movimentacoesEstoqueRepository = movimentacoesEstoqueRepository;
    }

    public List<Movimentacoes_Estoque> listarMovimentacoesEstoque() {
        return this.movimentacoesEstoqueRepository.findAll();
    }