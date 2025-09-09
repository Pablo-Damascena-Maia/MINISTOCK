package com.senac.ministock.service;

import com.senac.ministock.dto.request.ProdutoDTORequest;
import com.senac.ministock.dto.response.ProdutoDTOResponse;
import com.senac.ministock.dto.response.ProdutoDTOUpdateResponse;
import com.senac.ministock.entity.Produto;
import com.senac.ministock.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ModelMapper modelMapper) {
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProdutoDTOResponse> listarProdutos() {
        return produtoRepository.listarProdutos()
                .stream()
                .map(p -> modelMapper.map(p, ProdutoDTOResponse.class))
                .toList();
    }

    public ProdutoDTOResponse listarPorId(Integer id) {
        Produto p = produtoRepository.obterProdutoPeloId(id);
        return (p != null) ? modelMapper.map(p, ProdutoDTOResponse.class) : null;
    }

    @Transactional
    public ProdutoDTOResponse criarProduto(ProdutoDTORequest dto) {
        Produto p = modelMapper.map(dto, Produto.class);
        Produto salvo = produtoRepository.save(p);
        return modelMapper.map(salvo, ProdutoDTOResponse.class);
    }

    @Transactional
    public ProdutoDTOResponse atualizarProduto(Integer id, ProdutoDTORequest dto) {
        Produto p = produtoRepository.obterProdutoPeloId(id);
        if (p != null) {
            modelMapper.map(dto, p);
            Produto salvo = produtoRepository.save(p);
            return modelMapper.map(salvo, ProdutoDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ProdutoDTOUpdateResponse atualizarStatusProduto(Integer id, ProdutoDTORequest dto) {
        Produto p = produtoRepository.obterProdutoPeloId(id);
        if (p != null) {
            p.setStatus(dto.getStatus());
            Produto salvo = produtoRepository.save(p);
            return modelMapper.map(salvo, ProdutoDTOUpdateResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarProduto(Integer id) {
        produtoRepository.apagadoLogicoProduto(id);
    }
}
