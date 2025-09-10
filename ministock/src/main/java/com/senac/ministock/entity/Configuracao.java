package com.senac.ministock.entity;

import jakarta.persistence.*;



@Entity
@Table(name="configuracao")
public class Configuracao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="configuracao_id")
    private Integer id;
    @Column(name="configuracao_chave")
    private String chave;
    @Column(name="configuracao_valor")
    private String valor;
    @Column(name="configuracao_descricao")
    private String descricao;
    @Column(name="configuracao_status")
    private  Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
