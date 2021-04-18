package com.brikton.labapps.mspedido.repository;

import com.brikton.labapps.mspedido.domain.Pedido;

import frsf.isi.dan.InMemoryRepository;

public class PedidoRepository extends InMemoryRepository<Pedido> {

    @Override
    public Integer getId(Pedido entity) {
        return entity.getId();
    }

    @Override
    public void setId(Pedido entity, Integer id) {
        entity.setId(id);
    }
    
}
