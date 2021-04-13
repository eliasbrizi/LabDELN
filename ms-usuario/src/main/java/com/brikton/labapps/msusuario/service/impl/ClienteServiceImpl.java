package com.brikton.labapps.msusuario.service.impl;

import java.util.ArrayList;

import com.brikton.labapps.msusuario.domain.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {
    
    //TODO creo que hay que borrar todo esto
    private static final List<Cliente> listaClientes = new ArrayList<>();
    private static Integer ID_GEN = 1;

    @Autowired
    RiesgoBCRAService riesgoBCRAService;

    /**
    Da de alta un cliente cliente y valida
    situacion crediticia para habilitarlo online
    @param nuevoCliente
     */
    @Override
    public Cliente altaCliente(Cliente nuevoCliente){
        nuevoCliente.setId(ID_GEN++);
        // pido riesgo para habilitar online
        if (this.riesgoBCRAService.getRiesgo(nuevoCliente.getCuit()))
            nuevoCliente.setHabilitadoOnline(true);
        else nuevoCliente.setHabilitadoOnline(false);
        listaClientes.add(nuevoCliente);
        return nuevoCliente;
    }
}