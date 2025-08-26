package com.senac.ministock.entity;

import jakarta.persistence.*;

import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name="movimentacoes_estoque")
public class Movimentacoes_Estoque {
    @Id
    @GeneratedValue
    @Column(name="movimentacoes_estoque_id")
    private int id;
    @Column(name="movimentacoes_estoque_tipo")
    private Enum<> tipo;
    @Column(name="movimentacoes_estoque_quantidade")
    private int quantidade;
    @Column(name="movimentacoes_estoque_data_movimentacao")
    private Date data_movimentacao;
    @Column(name="movimentacoes_estoque_observacao")
    private String observacao;
    @Column(name="movimentacoes_estoque_preco_compra")
    private DecimalFormat preco_compra;
    @Column(name="movimentacoes_estoque_preco_venda")
    private  DecimalFormat preco_venda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Enum getTipo() {
        return tipo;
    }

    public void setTipo(Enum tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData_movimentacao() {
        return data_movimentacao;
    }

    public void setData_movimentacao(Date data_movimentacao) {
        this.data_movimentacao = data_movimentacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public DecimalFormat getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(DecimalFormat preco_compra) {
        this.preco_compra = preco_compra;
    }

    public DecimalFormat getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(DecimalFormat preco_venda) {
        this.preco_venda = preco_venda;
    }
}
