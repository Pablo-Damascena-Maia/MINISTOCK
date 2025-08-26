package com.senac.ministock.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="produto")
public class Produto {
    @Id
    @GeneratedValue
    @Column(name="produto_id")
    private int id;
    @Column(name="produto_nome")
    private String nome;
    @Column(name="produto_descricao")
    private String descricao;
    @Column(name="produto_data_entrada")
    private Date data_entrada;
    @Column(name="produto_quantidade_estoque")
    private int quantidade_estoque;
    @Column(name="produto_codigo_barras")
    private String codigo_barras;
    @Column(name="produto_ativo")
    private Boolean ativo;
    @Column(name="produto_data_criacao")
    private Date data_criacao;
    @Column(name="produto_criado_por")
    private int criado_por;
    @Column(name="produto_data_atualizacao")
    private Date data_atualizacao;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public int getCriado_por() {
        return criado_por;
    }

    public void setCriado_por(int criado_por) {
        this.criado_por = criado_por;
    }

    public Date getData_atualizacao() {
        return data_atualizacao;
    }

    public void setData_atualizacao(Date data_atualizacao) {
        this.data_atualizacao = data_atualizacao;
    }
}
