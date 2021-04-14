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

    @Autowired
    PedidoService pedidoService;

    @Override
    public Cliente guardarCliente(Cliente c) throws RecursoNoEncontradoException,RiesgoException {
        if (!(c.getId()!=null && c.getId()>0)) {
            if (riesgoBCRAService.getRiesgo() > 2) 
                throw new RiesgoException("Riesgo Crediticio > 2");
            c.setId(ID_GEN++);
            listaClientes.add(c);

        } else {
            OptionalInt indexOpt = IntStream.range(0 , listaClientes.size())
                .filter(i -> listaClientes.get(i).getId().equals(c.getId()))
                .findFirst();
            if (indexOpt.isPresent()) {
                listaClientes.set(indexOpt.getAsInt(), c);
            } else {
                throw new RecursoNoEncontradoException("Cliente",c.getId());
            }
        } 
        return c;
    }

    @Override
    public void bajaCliente(Integer id) throws RecursoNoEncontradoException {
        
        OptionalInt indexOpt = IntStream.range(0 , listaClientes.size())
            .filter(i -> listaClientes.get(i).getId().equals(id))
            .findFirst();

        if (indexOpt.isPresent()) {
            listaClientes.remove(indexOpt.getAsInt());
        } else {
            throw new RecursoNoEncontradoException("Cliente",id);
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        return listaClientes;
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Integer id) throws RecursoNoEncontradoException {
        
        OptionalCliente clienteOpt = listaClientes.stram()
            .filter( c -> c.getId().equals(id) ).findFirst();

        if (clienteOpt.isEmpty()) throw new RecursoNoEncontradoException("Cliente",id);
        
        return clienteOpt;
    }
}