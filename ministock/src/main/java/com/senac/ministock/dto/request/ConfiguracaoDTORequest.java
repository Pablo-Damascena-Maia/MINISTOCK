package com.senac.ministock.dto.request;
public class ConfiguracaoDTORequest {

    private String chave;


    private String valor;

    private String descricao;
    private Integer status;

    public void setStatus(Integer status) {
        this.status = status;
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
}
