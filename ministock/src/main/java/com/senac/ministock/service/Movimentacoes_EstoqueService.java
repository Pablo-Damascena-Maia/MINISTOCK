package com.senac.ministock.service;

import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTORequest;
import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTOUpdateRequest;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOResponse;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOUpdateResponse;
import com.senac.ministock.entity.Movimentacoes_Estoque;
import com.senac.ministock.repository.Movimentacoes_EstoqueRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class Movimentacoes_EstoqueService {

    private final Movimentacoes_EstoqueRepository repository;

    @Autowired
    private final ModelMapper modelMapper;

    public Movimentacoes_EstoqueService(Movimentacoes_EstoqueRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<Movimentacoes_EstoqueDTOResponse> listarMovimentacoes() {
        return repository.listarMovimentacoes()
                .stream()
                .map(m -> modelMapper.map(m, Movimentacoes_EstoqueDTOResponse.class))
                .toList();
    }

    public Movimentacoes_EstoqueDTOResponse listarPorId(Integer id) {
        Movimentacoes_Estoque m = repository.obterMovimentacaoPeloId(id);
        return (m != null) ? modelMapper.map(m, Movimentacoes_EstoqueDTOResponse.class) : null;
    }

    @Transactional
    public Movimentacoes_EstoqueDTOResponse criarMovimentacao(Movimentacoes_EstoqueDTORequest dto) {
        Movimentacoes_Estoque m = modelMapper.map(dto, Movimentacoes_Estoque.class);
        Movimentacoes_Estoque salvo = repository.save(m);
        return modelMapper.map(salvo, Movimentacoes_EstoqueDTOResponse.class);
    }

    @Transactional
    public Movimentacoes_EstoqueDTOResponse atualizarMovimentacao(Integer id, @Valid Movimentacoes_EstoqueDTOUpdateRequest dto) {
        Movimentacoes_Estoque m = repository.obterMovimentacaoPeloId(id);
        if (m != null) {
            modelMapper.map(dto, m);
            Movimentacoes_Estoque salvo = repository.save(m);
            return modelMapper.map(salvo, Movimentacoes_EstoqueDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public Movimentacoes_EstoqueDTOUpdateResponse atualizarStatusMovimentacao(Integer id, Movimentacoes_EstoqueDTORequest dto) {
        Movimentacoes_Estoque m = repository.obterMovimentacaoPeloId(id);
        if (m != null) {
            m.setStatus(dto.getStatus());
            Movimentacoes_Estoque salvo = repository.save(m);
            return modelMapper.map(salvo, Movimentacoes_EstoqueDTOUpdateResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarMovimentacao(Integer id) {
        repository.apagadoLogicoMovimentacao(id);
    }
}
