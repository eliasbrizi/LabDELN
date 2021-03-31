package com.brikton.labapps.mspedidos.domain;

public class DetallePedido {
    private Pedido pedido;
    private Producto producto;

    private Integer id;
    private Integer cantidad;
    private Double precio;

    public DetallePedido(Pedido pedido, Producto producto, Integer id, Integer cantidad, Double precio) {
        this.pedido = pedido;
        this.producto = producto;
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
