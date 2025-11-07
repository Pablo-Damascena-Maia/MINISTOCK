package com.senac.ministock.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categoria_produto")
public class CategoriaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_produto_id")
    private int id;
    @Column(name = "categoria_produto_nome")
    private String nome;
    @Column(name = "categoria_produto_status")
    private int status;
    @OneToMany(mappedBy = "categoriaProduto")
    private Set<Produto> produtos;

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

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
}
