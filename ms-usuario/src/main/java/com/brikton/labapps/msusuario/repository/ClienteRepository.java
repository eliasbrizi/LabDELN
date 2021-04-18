package com.brikton.labapps.msusuario.repository;

import com.brikton.labapps.msusuario.domain.Cliente;

import frsf.isi.dan.InMemoryRepository;

public class ClienteRepository extends InMemoryRepository<Cliente>{

    @Override
    public Integer getId(Cliente entity) {
        return entity.getId();
    }

    @Override
    public void setId(Cliente entity, Integer id) {
        entity.setId(id);
    }
    
}
