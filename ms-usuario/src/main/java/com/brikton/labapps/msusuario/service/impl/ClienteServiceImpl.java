package com.brikton.labapps.msusuario.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.exception.RecursoNoEncontradoException;
import com.brikton.labapps.msusuario.exception.RiesgoException;
import com.brikton.labapps.msusuario.repository.ClienteRepository;
import com.brikton.labapps.msusuario.service.ClienteService;
import com.brikton.labapps.msusuario.service.PedidoService;
import com.brikton.labapps.msusuario.service.RiesgoBCRAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static Integer ID_GEN = 1;

    @Autowired
    RiesgoBCRAService riesgoBCRAService;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ClienteRepository clienteRepository;
    
    @Override
    public Cliente guardarCliente(Cliente c) throws RecursoNoEncontradoException,RiesgoException{
        if ((c.getId()==null || c.getId()<1)) {
            if (riesgoBCRAService.getRiesgo(c.getCuit()) > 2) 
                throw new RiesgoException("Riesgo Crediticio > 2");
            c.setId(ID_GEN++);
            clienteRepository.save(c);

        } else {
            if (clienteRepository.existsById(c.getId())){
                clienteRepository.save(c);
            } else {
                throw new RecursoNoEncontradoException("Cliente",c.getId());
            }
        } 
        return c;
    }

    @Override
    public void bajaCliente(Integer id) throws RecursoNoEncontradoException {
        //TODO : si el cliente alguna vez pidio, se le setea la fecha de baja

        if (pedidoService.tienePedidos(id)) {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isEmpty()) {
                throw new RecursoNoEncontradoException("Cliente",id);
            } else {
                cliente.get().setFechaBaja(LocalDate.now());
            }
        } else {
            if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);;
            } else {
                throw new RecursoNoEncontradoException("Cliente",id);
            }
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        clientes.forEach( c -> { if ( c.getFechaBaja() == null ) listaClientes.add(c); });
        return listaClientes;
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Integer id) throws RecursoNoEncontradoException {
        if (!clienteRepository.existsById(id)) throw new RecursoNoEncontradoException("Cliente",id); 
        return clienteRepository.findById(id);
    }
    
    @Override
    public Optional<Cliente> buscarClientePorRazonSocial(String razonSocial) throws RecursoNoEncontradoException {
        //TODO implementar  
        return null;
    }

    @Override
    public Optional<Cliente> buscarClientePorCuit(String cuit) throws RecursoNoEncontradoException {
        // TODO Auto-generated method stub
        Iterable<Cliente> lista = clienteRepository.findAll();
        Optional<Cliente> cliente = null;
        for ( Cliente c : lista) if(c.getCuit().equals(cuit)) cliente = Optional.of(c);
        return cliente;
    }

}