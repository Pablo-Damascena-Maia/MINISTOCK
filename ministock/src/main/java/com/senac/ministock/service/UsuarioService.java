package com.senac.ministock.service;

import com.senac.ministock.dto.request.UsuarioDTORequest;
import com.senac.ministock.dto.response.UsuarioDTOResponse;
import com.senac.ministock.dto.response.UsuarioDTOUpdateResponse;
import com.senac.ministock.entity.Usuario;
import com.senac.ministock.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<UsuarioDTOResponse> listarUsuarios() {
        return usuarioRepository.listarUsuarios()
                .stream()
                .map(u -> modelMapper.map(u, UsuarioDTOResponse.class))
                .toList();
    }

    public UsuarioDTOResponse listarPorId(Integer id) {
        Usuario u = usuarioRepository.obterUsuarioPeloId(id);
        return (u != null) ? modelMapper.map(u, UsuarioDTOResponse.class) : null;
    }

    @Transactional
    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest dto) {
        Usuario u = modelMapper.map(dto, Usuario.class);
        Usuario salvo = usuarioRepository.save(u);
        return modelMapper.map(salvo, UsuarioDTOResponse.class);
    }

    @Transactional
    public UsuarioDTOResponse atualizarUsuario(Integer id, UsuarioDTORequest dto) {
        Usuario u = usuarioRepository.obterUsuarioPeloId(id);
        if (u != null) {
            modelMapper.map(dto, u);
            Usuario salvo = usuarioRepository.save(u);
            return modelMapper.map(salvo, UsuarioDTOResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public UsuarioDTOUpdateResponse atualizarStatusUsuario(Integer id, UsuarioDTORequest dto) {
        Usuario u = usuarioRepository.obterUsuarioPeloId(id);
        if (u != null) {
            u.setStatus(dto.getStatus());
            Usuario salvo = usuarioRepository.save(u);
            return modelMapper.map(salvo, UsuarioDTOUpdateResponse.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void apagarUsuario(Integer id) {
        usuarioRepository.apagadoLogicoUsuario(id);
    }
}
