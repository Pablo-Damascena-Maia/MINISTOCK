package com.senac.ministock.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.senac.ministock.dto.request.UsuarioDTORequest;
import com.senac.ministock.dto.request.UsuarioDTOUpdateRequest;
import com.senac.ministock.dto.response.UsuarioDTOResponse;
import com.senac.ministock.entity.Usuario;
import com.senac.ministock.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/listarPorId/{usuarioId}")
    @Operation(summary = "Listar usuário pelo id", description = "Endpoint para buscar usuário pelo id")
    public ResponseEntity<Usuario> listarPorId(@PathVariable("usuarioId") Integer usuarioId) {
        Usuario usuario = usuarioService.listarPorId(usuarioId);
        if (usuario == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar novo usuário", description = "Endpoint para criar um novo usuário")
    public ResponseEntity<UsuarioDTOResponse> criarUsuario(@Valid @RequestBody UsuarioDTORequest usuarioDTORequest) {
        UsuarioDTOResponse response = (UsuarioDTOResponse) usuarioService.criarUsuario(usuarioDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/atualizar/{usuarioId}")
    @Operation(summary = "Atualizar todos os dados do usuário", description = "Endpoint para atualizar todos os registros do usuário")
    public ResponseEntity<Object> atualizarUsuario(
            @PathVariable("usuarioId") Integer usuarioId,
            @RequestBody UsuarioDTOUpdateRequest usuarioDTOUpdateRequest) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioId, usuarioDTOUpdateRequest));
    }

    @PatchMapping("/atualizarStatus/{usuarioId}")
    @Operation(summary = "Atualizar campo de status do usuário", description = "Endpoint para atualizar apenas o status do usuário")
    public ResponseEntity<Object> atualizarStatusUsuario(
            @PathVariable("usuarioId") Integer usuarioId,
            @RequestBody UsuarioDTOUpdateRequest usuarioDTOUpdateRequest) {
        return ResponseEntity.ok(usuarioService.atualizarStatusUsuario(usuarioId, usuarioDTOUpdateRequest));
    }
}
