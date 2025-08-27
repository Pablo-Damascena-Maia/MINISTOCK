package com.senac.ministock.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name= "usuario")
public class Usuario {
    @Column(name="usuario_id")
    private int id;
    @Column(name="usuario_nome")
    private String nome;
    @Column(name="usuario_email")
    private String email;
    @Column(name="usuario_senha_hash")
    private String senha_hash;
    @Enumerated(EnumType.STRING)
    @Column(name="usuario_perfil")
    private  Perfil perfil;
    @Column(name="usuario_data_criacao")
    private Date data_criacao;
    @Column(name="usuario_ultimo_login")
    private Date ultimo_login;
    @OneToMany (mappedBy = "usuario")
    private Set<Notificacao> notificacaos;
    @OneToMany (mappedBy = "usuario")
    private Set<Produto> produtos;
    @OneToMany (mappedBy = "usuario")
    private Set<Movimentacoes_Estoque> movimentacoesEstoques;

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public Set<Movimentacoes_Estoque> getMovimentacoesEstoques() {
        return movimentacoesEstoques;
    }

    public void setMovimentacoesEstoques(Set<Movimentacoes_Estoque> movimentacoesEstoques) {
        this.movimentacoesEstoques = movimentacoesEstoques;
    }

    public Set<Notificacao> getNotificacaos() {
        return notificacaos;
    }

    public void setNotificacaos(Set<Notificacao> notificacaos) {
        this.notificacaos = notificacaos;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha_hash() {
        return senha_hash;
    }

    public void setSenha_hash(String senha_hash) {
        this.senha_hash = senha_hash;
    }


    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Date getUltimo_login() {
        return ultimo_login;
    }

    public void setUltimo_login(Date ultimo_login) {
        this.ultimo_login = ultimo_login;
    }
}
