package com.senac.ministock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="notificacao")
public class Notificacao {
    @Id
    @GeneratedValue
    @Column(name="notificacao_id")
    private Integer id;
    @Column(name="notificacao_titulo")
    private String titulo;
    @Column(name="notificacao_mensagem")
    private String mensagem;
    @Enumerated(EnumType.STRING)
    @Column(name="notificacao_tipo")
    private Tipo tipo;
    @Column(name="notificacao_lida")
    private Integer lida;
    @Column(name="notificacao_data_criacao")
    private Date data_criacao;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getLida() {
        return lida;
    }

    public void setLida(Integer lida) {
        this.lida = lida;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }
}
