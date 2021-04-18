package com.brikton.labapps.msusuario.rest;

import java.util.List;
import java.util.Optional;

import com.brikton.labapps.msusuario.domain.Cliente;
import com.brikton.labapps.msusuario.exception.OperacionNoPermitidaException;
import com.brikton.labapps.msusuario.exception.RecursoNoEncontradoException;
import com.brikton.labapps.msusuario.exception.RiesgoException;
import com.brikton.labapps.msusuario.service.ClienteService;

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
@RequestMapping("/api/cliente")
@Api(value = "ClienteRest", description = "Permite gestionar los clientes de la empresa")
public class ClienteRest {
    
    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<?> clientePorId(@PathVariable Integer id){
        
        Optional<Cliente> c = null;
        
        try{
            c = this.clienteService.buscarClientePorId(id);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(c.get());
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> todos(){
        return ResponseEntity.ok(this.clienteService.listarClientes());
    }

    @GetMapping( path = "/cuit")
    public ResponseEntity<?> clientePorCuit(@RequestParam String cuit){
                
        Optional<Cliente> c = null;
        
        try{
            c = this.clienteService.buscarClientePorCuit(cuit);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(c.get());
    }
    @GetMapping( path = "/razonSocial")
    public ResponseEntity<?> clientePorRazonSocial(@RequestParam( required = false ) String razonSocial){
        Optional<Cliente> c = null;
        
        try{
            c = this.clienteService.buscarClientePorRazonSocial(razonSocial);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok(c.get()); 
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Cliente clienteNuevo){
        
        /*
        Valido que tenga usuario y que tenga obras asociadas
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
        Cliente creado = null;
        try {
            creado = this.clienteService.guardarCliente(clienteNuevo);
        } catch (RecursoNoEncontradoException e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        } catch (RiesgoException e2) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage());
        }
        return ResponseEntity.ok(creado);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Actualizado correctamente"),
        @ApiResponse(code = 401, message = "No autorizado"),
        @ApiResponse(code = 403, message = "Prohibido"),
        @ApiResponse(code = 404, message = "El ID no existe")
    })
    public ResponseEntity<?> actualizar(@RequestBody Cliente nuevo,  @PathVariable Integer id){
        Cliente actualizado = null;
        try {
            actualizado = this.clienteService.guardarCliente(nuevo);
        } catch (RecursoNoEncontradoException e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        } catch (RiesgoException e2) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e2.getMessage());
        } 
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        try {
            this.clienteService.bajaCliente(id);
        } catch (RecursoNoEncontradoException e1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1.getMessage());
        } catch (OperacionNoPermitidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } 
        return ResponseEntity.ok().build();
    }
}