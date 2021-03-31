package com.brikton.labapps.mspedidos.domain;

public class EstadoPedido {
    private Pedido pedido;

    private Integer id;
    private String estado;

    public EstadoPedido(Pedido pedido, Integer id, String estado) {
        this.pedido = pedido;
        this.id = id;
        this.estado = estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
