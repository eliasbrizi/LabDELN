package com.brikton.labapps.mspedidos.domain;

public class Obra {
    private Pedido pedido;

    private Integer id;
    private String descripcion;

    public Obra(Pedido pedido, Integer id, String descripcion) {
        this.pedido = pedido;
        this.id = id;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
