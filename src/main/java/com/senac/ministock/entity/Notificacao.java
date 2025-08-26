package com.senac.ministock.entity;

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
    @Column(name="notificacao_tipo")
    private Enum<> tipo;
    @Column(name="notificacao_lida")
    private Integer lida;
    @Column(name="notificacao_data_criacao")
    private Date data_criacao;

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

    public Enum<E> getTipo() {
        return tipo;
    }

    public void setTipo(Enum<E> tipo) {
        this.tipo = tipo;
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
