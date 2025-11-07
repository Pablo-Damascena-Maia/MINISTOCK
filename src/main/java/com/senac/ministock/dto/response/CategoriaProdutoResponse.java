package com.senac.ministock.dto.response;

import com.senac.ministock.entity.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class CategoriaProdutoResponse {
    private int id;

    private String nome;

    private int status;
    private Integer produtoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }
}

