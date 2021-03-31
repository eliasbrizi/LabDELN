package com.brikton.labapps.mspedidos.domain;

import java.time.Instant;
import java.util.List;

public class Pedido {
    private EstadoPedido estadoPedido;
    private Obra obra;
    private List<DetallePedido> detallePedidos;

    private Integer id;
    private Instant fechaPedido;

    public Pedido(EstadoPedido estadoPedido, Obra obra, List<DetallePedido> detallePedidos, Integer id, Instant fechaPedido) {
        this.estadoPedido = estadoPedido;
        this.obra = obra;
        this.detallePedidos = detallePedidos;
        this.id = id;
        this.fechaPedido = fechaPedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Instant fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
