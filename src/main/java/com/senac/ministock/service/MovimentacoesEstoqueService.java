package com.senac.ministock.service;

import com.senac.ministock.dto.request.MovimentacoesEstoqueDTORequest;
import com.senac.ministock.dto.response.MovimentacoesEstoqueDTOResponse;
import com.senac.ministock.dto.response.MovimentacoesEstoqueDTOUpdateResponse;
import com.senac.ministock.entity.MovimentacoesEstoque;
import com.senac.ministock.entity.Produto;
import com.senac.ministock.entity.TipoM;
import com.senac.ministock.entity.Usuario;
import com.senac.ministock.repository.MovimentacoesEstoqueRepository;
import com.senac.ministock.repository.ProdutoRepository;
import com.senac.ministock.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class MovimentacoesEstoqueService {

    private final MovimentacoesEstoqueRepository movimentacoesRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    public MovimentacoesEstoqueService(MovimentacoesEstoqueRepository movimentacoesRepository,
                                       UsuarioRepository usuarioRepository,
                                       ProdutoRepository produtoRepository,
                                       ModelMapper modelMapper) {
        this.movimentacoesRepository = movimentacoesRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    public List<MovimentacoesEstoqueDTOResponse> listarMovimentacoes() {
        return movimentacoesRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, MovimentacoesEstoqueDTOResponse.class))
                .toList();
    }

    public MovimentacoesEstoqueDTOResponse listarPorId(Integer id) {
        MovimentacoesEstoque movimentacao = movimentacoesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movimentação não encontrada"));
        return modelMapper.map(movimentacao, MovimentacoesEstoqueDTOResponse.class);
    }

    @Transactional
    public MovimentacoesEstoqueDTOResponse criarMovimentacao(MovimentacoesEstoqueDTORequest dto) {
        MovimentacoesEstoque movimentacao = new MovimentacoesEstoque();
        movimentacao.setTipoM(dto.getTipoM() != null ? dto.getTipoM() : TipoM.AJUSTE);
        movimentacao.setQuantidade(dto.getQuantidade() != null ? dto.getQuantidade() : 0);
        movimentacao.setDataMovimentacao(dto.getDataMovimentacao() != null ? dto.getDataMovimentacao() : new Date());
        movimentacao.setObservacao(dto.getObservacao());
        movimentacao.setPrecoCompra(dto.getPrecoCompra());
        movimentacao.setPrecoVenda(dto.getPrecoVenda());
        movimentacao.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
            movimentacao.setUsuario(usuario);
        }

        if (dto.getProdutoId() != null) {
            Produto produto = produtoRepository.findById(dto.getProdutoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
            movimentacao.setProduto(produto);
        }

        movimentacoesRepository.save(movimentacao);
        return new MovimentacoesEstoqueDTOResponse(movimentacao);
    }



    @Transactional
    public MovimentacoesEstoqueDTOResponse atualizarMovimentacao(Integer id, MovimentacoesEstoqueDTORequest dto) {
        MovimentacoesEstoque movimentacao = movimentacoesRepository.obterMovimentacaoPeloId(id);
        movimentacao.setTipoM(dto.getTipoM() != null ? dto.getTipoM() : TipoM.AJUSTE);
        movimentacao.setQuantidade(dto.getQuantidade() != null ? dto.getQuantidade() : 0);
        movimentacao.setDataMovimentacao(dto.getDataMovimentacao() != null ? dto.getDataMovimentacao() : new Date());
        movimentacao.setObservacao(dto.getObservacao());
        movimentacao.setPrecoCompra(dto.getPrecoCompra());
        movimentacao.setPrecoVenda(dto.getPrecoVenda());
        movimentacao.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);



        modelMapper.map(dto, movimentacao);

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
            movimentacao.setUsuario(usuario);
        }

        if (dto.getProdutoId() != null) {
            Produto produto = produtoRepository.findById(dto.getProdutoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));
            movimentacao.setProduto(produto);
        }

        MovimentacoesEstoque atualizada = movimentacoesRepository.save(movimentacao);
        return modelMapper.map(atualizada, MovimentacoesEstoqueDTOResponse.class);
    }

    @Transactional
    public MovimentacoesEstoqueDTOUpdateResponse atualizarStatusMovimentacao(Integer id, MovimentacoesEstoqueDTORequest dto) {
        MovimentacoesEstoque movimentacao = movimentacoesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movimentação não encontrada"));

        movimentacao.setStatus(dto.getStatus());

        MovimentacoesEstoque atualizada = movimentacoesRepository.save(movimentacao);
        return modelMapper.map(atualizada, MovimentacoesEstoqueDTOUpdateResponse.class);
    }

    @Transactional
    public void apagarMovimentacao(Integer id) {
        MovimentacoesEstoque movimentacao = movimentacoesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movimentação não encontrada"));

        movimentacao.setStatus(-1); // apagado lógico
        movimentacoesRepository.save(movimentacao);
    }


}
