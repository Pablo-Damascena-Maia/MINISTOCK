package com.senac.ministock.service;

import com.senac.ministock.dto.request.CategoriaProdutoRequest;
import com.senac.ministock.dto.response.CategoriaProdutoResponse;
import com.senac.ministock.dto.response.ProdutoDTOUpdateResponse;
import com.senac.ministock.entity.CategoriaProduto;

import com.senac.ministock.repository.CategoriaProdutoRepository;
import com.senac.ministock.repository.ProdutoRepository;
import com.senac.ministock.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriaProdutoService {
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final CategoriaProdutoRepository categoriaProdutoRepository;

    @Autowired
    public CategoriaProdutoService(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper, CategoriaProdutoRepository categoriaProdutoRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.categoriaProdutoRepository = categoriaProdutoRepository;
    }

    public List<CategoriaProdutoResponse> listarCategoriaProdutos() {
        return categoriaProdutoRepository.listarCategoriaProduto()
                .stream()
                .map(cp -> modelMapper.map(cp, CategoriaProdutoResponse.class))
                .toList();
    }

    public CategoriaProdutoResponse listarPorId(Integer id) {
        CategoriaProduto cp = categoriaProdutoRepository.obterCategoriaProdutoPeloId(id);
        return (cp != null) ? modelMapper.map(cp, CategoriaProdutoResponse.class) : null;
    }

    @Transactional
    public CategoriaProdutoResponse criarCategoriaProduto(CategoriaProdutoRequest dto) {
        CategoriaProduto cp = new CategoriaProduto();
        cp.setNome(dto.getNome());
        cp.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);

        CategoriaProduto salvo = categoriaProdutoRepository.save(cp);
        CategoriaProdutoResponse response = new CategoriaProdutoResponse();
        modelMapper.map(salvo, response);
        return response;
    }

    @Transactional
    public CategoriaProdutoResponse atualizarCategoriaProduto(Integer id, CategoriaProdutoRequest dto) {
        CategoriaProduto cp = categoriaProdutoRepository.obterCategoriaProdutoPeloId(id);
        if (cp == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CategoriaProduto não encontrado");
        cp.setNome(dto.getNome());
        if (dto.getStatus() != null) cp.setStatus(dto.getStatus());

        CategoriaProduto salvo = categoriaProdutoRepository.save(cp);
        return modelMapper.map(salvo, CategoriaProdutoResponse.class);
    }

    @Transactional
    public ProdutoDTOUpdateResponse atualizarStatusCategoriaProduto(Integer id, CategoriaProdutoRequest dto) {
        CategoriaProduto cp = categoriaProdutoRepository.obterCategoriaProdutoPeloId(id);
        if (cp == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CategoriaProduto não encontrado");

        if (dto.getStatus() != null) cp.setStatus(dto.getStatus());

        CategoriaProduto salvo = categoriaProdutoRepository.save(cp);
        return modelMapper.map(salvo, ProdutoDTOUpdateResponse.class);
    }

    public void apagarCategoriaProduto(Integer id) {
        produtoRepository.apagadoLogicoProduto(id);
    }
}


