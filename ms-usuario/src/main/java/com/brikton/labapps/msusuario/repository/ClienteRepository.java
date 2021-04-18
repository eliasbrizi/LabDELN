package com.brikton.labapps.msusuario.repository;

import com.brikton.labapps.msusuario.domain.Cliente;

import org.springframework.stereotype.Repository;

import frsf.isi.dan.InMemoryRepository;

@Repository
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
