package com.senac.ministock.entity;

import jakarta.persistence.*;

import javax.swing.*;

@Entity
@Table(name="configuracao")
public class Configuracao {
    @Id
    @GeneratedValue
    @Column(name="configuracao_id")
    private Integer id;
    @Column(name="configuracao_chave")
    private Spring chave;
    @Column(name="configuracao_valor")
    private String valor;
    @Column(name="configuracao_descricao")
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Spring getChave() {
        return chave;
    }

    public void setChave(Spring chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
