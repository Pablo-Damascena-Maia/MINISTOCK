package com.senac.ministock.service;

import com.senac.ministock.repository.UsuarioRepository;
import com.senac.ministock.dto.request.UsuarioDTORequest;
import com.senac.ministock.dto.request.UsuarioDTOUpdateRequest;
import com.senac.ministock.dto.response.UsuarioDTOResponse;
import com.senac.ministock.dto.response.UsuarioDTOUpdateResponse;
import com.senac.ministock.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario listarPorId(Integer usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        return usuario.orElse(null);
    }

    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTORequest.getNome());
        usuario.setEmail(usuarioDTORequest.getEmail());
        usuario.setSenha_hash(usuarioDTORequest.getSenha());
        usuario.setStatus(usuarioDTORequest.getStatus());
        // Adicione outros campos conforme necessário

        Usuario saved = usuarioRepository.save(usuario);

        UsuarioDTOResponse response = new UsuarioDTOResponse();
        response.setId(saved.getId());
        response.setNome(saved.getNome());
        response.setEmail(saved.getEmail());
        response.setStatus(saved.getStatus());
        // Adicione outros campos conforme necessário

        return response;
    }

    public UsuarioDTOResponse atualizarUsuario(Integer usuarioId, UsuarioDTOUpdateRequest usuarioDTOUpdateRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setNome(usuarioDTOUpdateRequest.getNome());
            usuario.setEmail(usuarioDTOUpdateRequest.getEmail());
            usuario.setSenha_hash(usuarioDTOUpdateRequest.getSenha());
            usuario.setStatus(usuarioDTOUpdateRequest.getStatus());
            // Atualize outros campos conforme necessário

            Usuario updated = usuarioRepository.save(usuario);

            UsuarioDTOResponse response = new UsuarioDTOResponse();
            response.setId(updated.getId());
            response.setNome(updated.getNome());
            response.setEmail(updated.getEmail());
            response.setStatus(updated.getStatus());
            // Adicione outros campos conforme necessário

            return response;
        }
        return null;
    }

    public UsuarioDTOUpdateResponse atualizarStatusUsuario(Integer usuarioId, UsuarioDTOUpdateRequest usuarioDTOUpdateRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setStatus(usuarioDTOUpdateRequest.getStatus());

            Usuario updated = usuarioRepository.save(usuario);

            UsuarioDTOUpdateResponse response = new UsuarioDTOUpdateResponse();
            response.setId(updated.getId());
            response.setStatus(updated.getStatus());

            return response;
        }
        return null;
    }
}
