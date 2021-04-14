package com.brikton.labapps.msusuario.service;

public interface ClienteService {

     public Cliente guardarCliente(Cliente c) 
          throws RecursoNoEncontradoException, RiesgoException;
     public void bajaCliente(Integer id)  
          throws RecursoNoEncontradoException, OperacionNoPermitidaException;
     public List<Cliente> listarClientes();
     public Optional<Cliente> buscarClientePorId(Integer id)
          throws RecursoNoEncontradoException;
     
}