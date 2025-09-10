package com.senac.ministock.service;

import com.senac.ministock.dto.request.Movimentacoes_EstoqueDTORequest;
import com.senac.ministock.dto.response.Movimentacoes_EstoqueDTOResponse;
import com.senac.ministock.entity.Movimentacoes_Estoque;
import com.senac.ministock.entity.Usuario;
import com.senac.ministock.repository.Movimentacoes_EstoqueRepository;
import com.senac.ministock.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Movimentacoes_EstoqueService {

    private final Movimentacoes_EstoqueRepository movimentacoes_EstoqueRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public Movimentacoes_EstoqueService(Movimentacoes_EstoqueRepository movimentacoes_EstoqueRepository,
                                        UsuarioRepository usuarioRepository,
                                        ModelMapper modelMapper) {
        this.movimentacoes_EstoqueRepository = movimentacoes_EstoqueRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public Movimentacoes_EstoqueDTOResponse criarMovimentacao(Movimentacoes_EstoqueDTORequest dto) {
        Movimentacoes_Estoque movimentacao = modelMapper.map(dto, Movimentacoes_Estoque.class);

        // Passo 1: buscar o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        movimentacao.setUsuario(usuario);

        Movimentacoes_Estoque salva = movimentacoes_EstoqueRepository.save(movimentacao);
        return modelMapper.map(salva, Movimentacoes_EstoqueDTOResponse.class);
    }

    public List<Movimentacoes_EstoqueDTOResponse> listarMovimentacoes() {
        return movimentacoes_EstoqueRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, Movimentacoes_EstoqueDTOResponse.class))
                .collect(Collectors.toList());
    }

    public Movimentacoes_EstoqueDTOResponse atualizarMovimentacao(int id, Movimentacoes_EstoqueDTORequest dto) {
        Movimentacoes_Estoque movimentacao = movimentacoes_EstoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        movimentacao.setUsuario(usuario);

        movimentacao.setTipo(dto.getTipo());
        movimentacao.setQuantidade(dto.getQuantidade());
        movimentacao.setData_movimentacao(dto.getData_movimentacao());
        movimentacao.setObservacao(dto.getObservacao());
        movimentacao.setPreco_compra(dto.getPreco_compra());
        movimentacao.setPreco_venda(dto.getPreco_venda());
        movimentacao.setStatus(dto.getStatus());

        Movimentacoes_Estoque atualizada = movimentacoes_EstoqueRepository.save(movimentacao);
        return modelMapper.map(atualizada, Movimentacoes_EstoqueDTOResponse.class);
    }

    public void removerMovimentacao(int id) {
        Movimentacoes_Estoque movimentacao = movimentacoes_EstoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
        movimentacoes_EstoqueRepository.delete(movimentacao);
    }
}
