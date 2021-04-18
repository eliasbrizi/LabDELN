package com.brikton.labapps.msusuario.service;

import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.exception.*;

public interface ClienteService {

     public Cliente guardarCliente(Cliente c) 
          throws RecursoNoEncontradoException, RiesgoException;
     public void bajaCliente(Integer id)  
          throws RecursoNoEncontradoException, OperacionNoPermitidaException;
     public List<Cliente> listarClientes();
     public Optional<Cliente> buscarClientePorId(Integer id)
          throws RecursoNoEncontradoException;
     public Optional<Cliente> buscarClientePorCuit(String cuit)
          throws RecursoNoEncontradoException;
     public Optional<Cliente> buscarClientePorRazonSocial(String razonSocial)
          throws RecursoNoEncontradoException;
}