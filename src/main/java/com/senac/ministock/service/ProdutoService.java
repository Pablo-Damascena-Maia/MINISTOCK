package com.senac.ministock.service;

import com.senac.ministock.repository.ProdutoRepository;
import com.senac.ministock.entity.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
         return this.produtoRepository.findAll();
    }
}