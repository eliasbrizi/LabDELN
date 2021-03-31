package com.brikton.labapps.mspedidos.domain;

public class Producto {
    private DetallePedido detallePedido;

    private Integer id;
    private String descripcion;
    private Double precio;

    public Producto(DetallePedido detallePedido, Integer id, String descripcion, Double precio) {
        this.detallePedido = detallePedido;
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
