package com.senac.ministock.dto.response;

public class ConfiguracaoDTOUpdateResponse {
    private int id;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = (int) status;
    }
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
