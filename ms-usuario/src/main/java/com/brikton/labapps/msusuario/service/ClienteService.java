package com.brikton.labapps.msusuario.service;

import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Cliente;

public interface ClienteService {

     public Cliente guardarCliente(Cliente c) 
          throws RecursoNoEncontradoException, RiesgoException;
     public void bajaCliente(Integer id)  
          throws RecursoNoEncontradoException, OperacionNoPermitidaException;
     public List<Cliente> listarClientes();
     public Optional<Cliente> buscarClientePorId(Integer id)
          throws RecursoNoEncontradoException;
     
}