package com.senac.ministock.service;

import com.senac.ministock.repository.ProdutoRepository;
import com.senac.ministock.dto.request.ProdutoDTORequest;
import com.senac.ministock.dto.request.ProdutoDTOUpdateRequest;
import com.senac.ministock.dto.response.ProdutoDTOResponse;
import com.senac.ministock.dto.response.ProdutoDTOUpdateResponse;
import com.senac.ministock.entity.Produto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto listarPorId(Integer produtoId) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);
        return produto.orElse(null);
    }

    public ProdutoDTOResponse criarProduto(ProdutoDTORequest produtoDTORequest) {
        Produto produto = new Produto();
        produto.setNome(produtoDTORequest.getNome());
        produto.setDescricao(produtoDTORequest.getDescricao());
        produto.setPreco(produtoDTORequest.getPreco());
        produto.setQuantidade_estoque(produtoDTORequest.getQuantidadeEstoque());
        produto.setStatus(produtoDTORequest.getStatus());
        // Adicione outros campos conforme necessário

        Produto saved = produtoRepository.save(produto);

        ProdutoDTOResponse response = new ProdutoDTOResponse();
        response.setId(saved.getId());
        response.setNome(saved.getNome());
        response.setDescricao(saved.getDescricao());
        response.setPreco(saved.getPreco());
        response.setQuantidadeEstoque(saved.getQuantidade_estoque());
        response.setStatus(saved.getStatus());
        // Adicione outros campos conforme necessário

        return response;
    }

    public ProdutoDTOResponse atualizarProduto(Integer produtoId, ProdutoDTOUpdateRequest produtoDTOUpdateRequest) {
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            produto.setNome(produtoDTOUpdateRequest.getNome());
            produto.setDescricao(produtoDTOUpdateRequest.getDescricao());
            produto.setPreco(produtoDTOUpdateRequest.getPreco());
            produto.setQuantidade_estoque(produtoDTOUpdateRequest.getQuantidade_estoque());
            produto.setStatus(produtoDTOUpdateRequest.getStatus());
            // Atualize outros campos conforme necessário

            Produto updated = produtoRepository.save(produto);

            ProdutoDTOResponse response = new ProdutoDTOResponse();
            response.setId(updated.getId());
            response.setNome(updated.getNome());
            response.setDescricao(updated.getDescricao());
            response.setPreco(updated.getPreco());
            response.setQuantidadeEstoque(updated.getQuantidade_estoque());
            response.setStatus(updated.getStatus());
            // Adicione outros campos conforme necessário

            return response;
        }
        return null;
    }

    public ProdutoDTOUpdateResponse atualizarStatusProduto(Integer produtoId, ProdutoDTOUpdateRequest produtoDTOUpdateRequest) {
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            produto.setStatus(produtoDTOUpdateRequest.getStatus());

            Produto updated = produtoRepository.save(produto);

            ProdutoDTOUpdateResponse response = new ProdutoDTOUpdateResponse();
            response.setId(updated.getId());
            response.setStatus(updated.getStatus());

            return response;
        }
        return null;
    }
}
