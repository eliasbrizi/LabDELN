package com.brikton.labapps.msusuario.service.impl;

import java.util.ArrayList;

import com.brikton.labapps.msusuario.domain.Cliente;

public class ClienteServiceImpl implements ClienteService {
    
    //TODO creo que hay que borrar todo esto
    private static final List<Cliente> listaClientes = new ArrayList<>();
    private static Integer ID_GEN = 1;

    /**
    Da de alta un cliente cliente y valida
    situacion crediticia para habilitarlo online
    @param nuevoCliente
     */
    public Cliente altaCliente(Cliente nuevoCliente){
        nuevoCliente.setId(ID_GEN++);
        // pido riesgo para habilitar online
        if (RiesgoBCRAServiceImpl.getRiesgo(nuevoCliente.getCuit()))
            nuevoCliente.setHabilitadoOnline(true);
        else nuevoCliente.setHabilitadoOnline(false);
        listaClientes.add(nuevoCliente);
        return nuevoCliente;
    }
}