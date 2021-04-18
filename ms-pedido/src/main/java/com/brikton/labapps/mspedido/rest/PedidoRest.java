package com.brikton.labapps.mspedido.rest;

import com.brikton.labapps.mspedido.domain.Pedido;
import com.brikton.labapps.mspedido.exception.RiesgoException;
import com.brikton.labapps.mspedido.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pedido")
@Api(value = "PediroRest", description = "Permite gestionar los pedidos de la empresa")
public class PedidoRest {
    
    @Autowired
    PedidoService pedidoService;

    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Creado correctamente"),
        @ApiResponse(code = 400, message = "El pedido no es correcto")})
    public ResponseEntity<?> crearPedido(@RequestBody Pedido nuevoPedido){
        
        /*
        Valido que obra, detalle y el detalle tenga productos y cantidad
        */

    //TODO
        Pedido creado = null;
        if ((nuevoPedido.getObra() != null)) {
            try {
                creado = this.pedidoService.crearPedido(nuevoPedido);
            } catch (RiesgoException e2) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage());
            }
            return ResponseEntity.ok(creado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}