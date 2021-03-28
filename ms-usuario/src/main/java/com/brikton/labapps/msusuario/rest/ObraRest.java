package com.brikton.labapps.msusuario.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import com.brikton.labapps.msusuario.domain.Obra;

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
@RequestMapping("/api/obra")
@Api(value = "ObraRest", description = "Permite gestionar las obras")
public class ObraRest {

    private static final List<Obra> listaObras = new ArrayList<Obra>();
    private static Integer ID_GEN = 1;

    @PostMapping
    @ApiOperation(value = "Crea una nueva obra")
    public ResponseEntity<Obra> crear(@RequestBody Obra nueva){
        System.out.println("Crear nueva obra: " + nueva);
        nueva.setId(ID_GEN++);
        listaObras.add(nueva);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza una obra")
    public ResponseEntity<Obra> actualizar(@RequestBody Obra nueva, @PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaObras.size())
        .filter(i -> listaObras.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            listaObras.set(indexOpt.getAsInt(), nueva);
            return ResponseEntity.ok(nueva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Borra una obra por su id")

    public ResponseEntity<Obra> borrar(@PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaObras.size())
        .filter(i -> listaObras.get(i).getId().equals(id))
        .findFirst();

        if(indexOpt.isPresent()){
            listaObras.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/{id}")
    @ApiOperation(value = "Obtiene una obra por id")
    public ResponseEntity<Obra> obraPorId(@PathVariable Integer id) {
        OptionalInt indexOpt =   IntStream.range(0, listaObras.size())
        .filter(i -> listaObras.get(i).getId().equals(id))
        .findFirst();
        if (indexOpt.isPresent()){
            return ResponseEntity.ok(listaObras.get(indexOpt.getAsInt()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ApiOperation( value = "Busca obras segun cuit de cliente && tipo de obra")
    public ResponseEntity<List<Obra>> obrasPorParametros(@RequestParam(required = false) String cliente, @RequestParam(required = false) String tipoObra) {
        ArrayList<Obra> obrasPorParametros = new ArrayList<>();
        obrasPorParametros.addAll(listaObras);
        if ( cliente != null){
            obrasPorParametros.removeIf(o -> o.getCliente().getCuit() != cliente);
        }
        if ( tipoObra != null) {
            obrasPorParametros.removeIf(o -> o.getTipo().getDescripcion() != tipoObra);
        }
        return ResponseEntity.ok(obrasPorParametros);
    }
}
