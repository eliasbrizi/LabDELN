package com.brikton.labapps.msusuario.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.brikton.labapps.msusuario.domain.Empleado;

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

@RestController
@RequestMapping("/api/empleado")
@Api(value = "EmpleadoRest", description = "Permite gestionar los empleados")
public class EmpleadoRest {
    
    private static final List<Empleado> listaEmpleados = new ArrayList<Empleado>();
    private static Integer ID_GEN = 1;

    @PostMapping
    @ApiOperation(value = "Crea un nuevo empleado")
    public ResponseEntity<Empleado> crear(@RequestBody Empleado nuevo){
        System.out.println("Crear nuevo empleado: " + nuevo);
        nuevo.setId(ID_GEN++);
        listaEmpleados.add(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un empleado")
    public ResponseEntity<Empleado> actualizar(@RequestBody Empleado nuevo, @PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaEmpleados.size())
        .filter(i -> listaEmpleados.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            listaEmpleados.set(indexOpt.getAsInt(), nuevo);
            return ResponseEntity.ok(nuevo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Borra un empleado por id")
    public ResponseEntity<Empleado> borrar(@PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaEmpleados.size())
        .filter(i -> listaEmpleados.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            listaEmpleados.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/{id}")
    @ApiOperation(value = "Busca un empleado por id")
    public ResponseEntity<Empleado> empleadoPorId(@PathVariable Integer id) {
        OptionalInt indexOpt =   IntStream.range(0, listaEmpleados.size())
        .filter(i -> listaEmpleados.get(i).getId().equals(id))
        .findFirst();
        if (indexOpt.isPresent()){
            return ResponseEntity.ok(listaEmpleados.get(indexOpt.getAsInt()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ApiOperation( value = "Busca empleado segun nombre de usuario")
    public ResponseEntity<Empleado> empleadoPorNombre(@RequestParam(required = false) String usuario) {
        if ( usuario != null){
            OptionalInt indexOpt =   IntStream.range(0, listaEmpleados.size())
            .filter(i -> listaEmpleados.get(i).getUser().getUser().equals(usuario))
            .findFirst();
            if (indexOpt.isPresent()){
                return ResponseEntity.ok(listaEmpleados.get(indexOpt.getAsInt()));
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
