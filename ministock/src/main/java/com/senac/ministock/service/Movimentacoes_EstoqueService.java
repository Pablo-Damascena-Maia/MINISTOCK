package com.senac.ministock.service;

import com.senac.ministock.repository.Movimentacoes_EstoqueRepository;
import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTORequest;
import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTOUpdateRequest;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOResponse;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOUpdateResponse;
import com.senac.ministock.entity.Movimentacoes_Estoque;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Movimentacoes_EstoqueService {
    private final Movimentacoes_EstoqueRepository movimentacoesEstoqueRepository;

    public Movimentacoes_EstoqueService(Movimentacoes_EstoqueRepository movimentacoesEstoqueRepository) {
        this.movimentacoesEstoqueRepository = movimentacoesEstoqueRepository;
    }

    public List<Movimentacoes_Estoque> listarMovimentacoesEstoque() {
        return movimentacoesEstoqueRepository.findAll();
    }

    public Movimentacoes_Estoque listarPorId(Integer movimentacaoId) {
        Optional<Movimentacoes_Estoque> movimentacao = movimentacoesEstoqueRepository.findById(movimentacaoId);
        return movimentacao.orElse(null);
    }

    public Movimentacoes_EstoqueDTOResponse criarMovimentacao(Movimentacoes_EstoqueDTORequest movimentacaoDTORequest) {
        Movimentacoes_Estoque movimentacao = new Movimentacoes_Estoque();
        movimentacao.setTipo(movimentacaoDTORequest.getTipo());
        movimentacao.setQuantidade(movimentacaoDTORequest.getQuantidade());
        movimentacao.setStatus(movimentacaoDTORequest.getStatus());
        // Adicione outros campos conforme necessário

        Movimentacoes_Estoque saved = movimentacoesEstoqueRepository.save(movimentacao);

        Movimentacoes_EstoqueDTOResponse response = new Movimentacoes_EstoqueDTOResponse();
        response.setId(saved.getId());
        response.setTipo(saved.getTipo());
        response.setQuantidade(saved.getQuantidade());
        response.setStatus(saved.getStatus());
        // Adicione outros campos conforme necessário

        return response;
    }

    public Movimentacoes_EstoqueDTOResponse atualizarMovimentacao(Integer movimentacaoId, Movimentacoes_EstoqueDTORequest movimentacaoDTORequest) {
        Optional<Movimentacoes_Estoque> movimentacaoOpt = movimentacoesEstoqueRepository.findById(movimentacaoId);
        if (movimentacaoOpt.isPresent()) {
            Movimentacoes_Estoque movimentacao = movimentacaoOpt.get();
            movimentacao.setTipo(movimentacaoDTORequest.getTipo());
            movimentacao.setQuantidade(movimentacaoDTORequest.getQuantidade());
            movimentacao.setStatus(movimentacaoDTORequest.getStatus());
            // Atualize outros campos conforme necessário

            Movimentacoes_Estoque updated = movimentacoesEstoqueRepository.save(movimentacao);

            Movimentacoes_EstoqueDTOResponse response = new Movimentacoes_EstoqueDTOResponse();
            response.setId(updated.getId());
            response.setTipo(updated.getTipo());
            response.setQuantidade(updated.getQuantidade());
            response.setStatus(updated.getStatus());
            // Adicione outros campos conforme necessário

            return response;
        }
        return null;
    }

    public Movimentacoes_EstoqueDTOUpdateResponse atualizarStatusMovimentacao(Integer movimentacaoId, Movimentacoes_EstoqueDTOUpdateRequest movimentacaoDTOUpdateRequest) {
        Optional<Movimentacoes_Estoque> movimentacaoOpt = movimentacoesEstoqueRepository.findById(movimentacaoId);
        if (movimentacaoOpt.isPresent()) {
            Movimentacoes_Estoque movimentacao = movimentacaoOpt.get();
            movimentacao.setStatus(movimentacaoDTOUpdateRequest.getStatus());

            Movimentacoes_Estoque updated = movimentacoesEstoqueRepository.save(movimentacao);

            Movimentacoes_EstoqueDTOUpdateResponse response = new Movimentacoes_EstoqueDTOUpdateResponse();
            response.setId(updated.getId());
            response.setStatus(updated.getStatus());

            return response;
        }
        return null;
    }
}