package com.brikton.labapps.msusuario.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.brikton.labapps.mspedido.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pedido")
@Api(value = "PediroRest", description = "Permite gestionar los pedidos de la empresa")
public class PedidoRest {
    
    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Pedido nuevoPedido){
        
        /*
        Valido que obra, detalle y el detalle tenga productos y cantidad
        */
//        if ((clienteNuevo.getUser() != null) &&
//            (clienteNuevo.getObras() != null) &&
//            (!clienteNuevo.getObras().isEmpty())) {
//            ClienteService.crearCliente(clienteNuevo);
//            //listaClientes.add(clienteNuevo);
//            return ResponseEntity.ok(clienteNuevo);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    //TODO

        if ((nuevoPedido.getObras().size() > 0))
        Pedido creado = null;
        try {
            creado = this.pedidoService.crearPedido(nuevoPedido);
        } catch (RecursoNoEncontradoException e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        } catch (RiesgoException e2) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage();
        }
        return ResponseEntity.ok(creado);
    }

}