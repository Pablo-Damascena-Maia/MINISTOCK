package com.senac.ministock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

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
    @Column(name="usuario_perfil")
    private Enum<> perfil;
    @Column(name="usuario_data_criacao")
    private Date data_criacao;
    @Column(name="usuario_ultimo_login")
    private Date ultimo_login;

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

    public Enum getPerfil() {
        return perfil;
    }

    public void setPerfil(Enum perfil) {
        this.perfil = perfil;
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
