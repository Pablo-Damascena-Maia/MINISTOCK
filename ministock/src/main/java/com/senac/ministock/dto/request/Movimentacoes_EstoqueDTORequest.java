package com.senac.ministock.dto.request;

import com.senac.ministock.entity.Tipo;

import java.util.Date;

public class Movimentacoes_EstoqueDTORequest {
    private Tipo tipo;
    private int usuarioId;
    private int quantidade;
    private Date data_movimentacao;
    private String observacao;
    private Double preco_compra;
    private Double preco_venda;
    private Integer status;


    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Date getData_movimentacao() { return data_movimentacao; }
    public void setData_movimentacao(Date data_movimentacao) { this.data_movimentacao = data_movimentacao; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    public Double getPreco_compra() { return preco_compra; }
    public void setPreco_compra(Double preco_compra) { this.preco_compra = preco_compra; }
    public Double getPreco_venda() { return preco_venda; }
    public void setPreco_venda(Double preco_venda) { this.preco_venda = preco_venda; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}
