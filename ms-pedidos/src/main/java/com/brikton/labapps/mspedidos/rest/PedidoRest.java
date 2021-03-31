package com.brikton.labapps.mspedidos.rest;

import com.brikton.labapps.mspedidos.domain.DetallePedido;
import com.brikton.labapps.mspedidos.domain.Pedido;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/pedido")
@Api(tags = {"PedidoRest"})
@SwaggerDefinition(tags = {
        @Tag(name = "PedidoRest", description = "Permite gestionar las operaciones de un Pedido")
        //https://stackoverflow.com/questions/38074936/api-annotations-description-is-deprecated
})
public class PedidoRest {

    private static final List<Pedido> PEDIDOS = new ArrayList<>();
    private static Integer ID_GEN = 1;

    @PostMapping
    @ApiOperation(value = "Crea un pedido")
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        pedido.setId(ID_GEN++);
        PEDIDOS.add(pedido);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping(path = "/{idPedido}/detalle")
    @ApiOperation(value = "Añade un detalle (item) a un pedido")
    public ResponseEntity<DetallePedido> addDetalle(@Nullable @PathVariable Integer idPedido, @RequestBody DetallePedido detalle) {
        Optional<Pedido> p = PEDIDOS
                .stream()
                .filter(pedido -> pedido.getId().equals(idPedido))
                .findFirst();

        if (p.isPresent()) {
            p.get().getDetallePedidos().add(detalle);
            detalle.setPedido(p.get());
            return ResponseEntity.ok(detalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{idPedido}")
    @ApiOperation(value = "Actualiza un pedido por su id")
    public ResponseEntity<Pedido> update(@RequestBody Pedido nuevo, @PathVariable Integer idPedido) {
        OptionalInt indexPed = IntStream.range(0, PEDIDOS.size())
                .filter(i -> PEDIDOS.get(i).getId().equals(idPedido))
                .findFirst();

        if (indexPed.isPresent()) {
            PEDIDOS.set(indexPed.getAsInt(), nuevo);
            return ResponseEntity.ok(nuevo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{idPedido}")
    @ApiOperation(value = "Borra una pedido por su id")
    public ResponseEntity<Pedido> delete(@PathVariable Integer idPedido) {
        OptionalInt indexPed = IntStream.range(0, PEDIDOS.size())
                .filter(i -> PEDIDOS.get(i).getId().equals(idPedido))
                .findFirst();

        if (indexPed.isPresent()) {
            PEDIDOS.remove(indexPed.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{idPedido}/detalle/{idDetalle}")
    @ApiOperation(value = "Borra un detalle del pedido")
    public ResponseEntity<Pedido> deleteDetalle(@PathVariable Integer idPedido, @PathVariable Integer idDetalle) {
        Optional<Pedido> p = PEDIDOS
                .stream()
                .filter(pedido -> pedido.getId().equals(idPedido))
                .findFirst();

        if (p.isPresent()) {
            OptionalInt indexDet = IntStream.range(0, p.get().getDetallePedidos().size())
                    .filter(i -> p.get().getDetallePedidos().get(i).getId().equals(idDetalle))
                    .findFirst();

            if (indexDet.isPresent()) {
                p.get().getDetallePedidos().remove(indexDet.getAsInt());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/{idPedido}")
    @ApiOperation(value = "Obtiene un pedido por su id")
    public ResponseEntity<Pedido> get(@PathVariable Integer idPedido) {

        Optional<Pedido> p = PEDIDOS
                .stream()
                .filter(pedido -> pedido.getId().equals(idPedido))
                .findFirst();

        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/obra/{idObra}")
    @ApiOperation(value = "Obtiene un pedido por el id de la obra")
    public ResponseEntity<Pedido> getByObra(@PathVariable Integer idObra) {
        Optional<Pedido> p = PEDIDOS
                .stream()
                .filter(pedido -> pedido.getObra().getId().equals(idObra))
                .findFirst();

        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/cliente/{idCliente}")
    @ApiOperation(value = "Obtiene un pedido por el id/cuit del cliente")
    public ResponseEntity<Pedido> getByCliente(@PathVariable Integer idCliente) {
        //ToDo hacer
        Optional<Pedido> p = PEDIDOS
                .stream()
                .filter(pedido -> pedido.getObra().getId().equals(idCliente))
                .findFirst();

        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{idPedido}/detalle/{idDetalle}")
    @ApiOperation(value = "Obtiene un detalle por id")
    public ResponseEntity<DetallePedido> getDetalle(@PathVariable Integer idPedido, @PathVariable Integer idDetalle) {
        Optional<Pedido> p = PEDIDOS
                .stream()
                .filter(pedido -> pedido.getId().equals(idPedido))
                .findFirst();

        if (p.isPresent()) {


            Optional<DetallePedido> det = p.get().getDetallePedidos()
                    .stream()
                    .filter(detalle -> detalle.getId().equals(idDetalle))
                    .findFirst();

            return det.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
        GET: /api/pedido/
            iii. Por Cuit y/o ID de Cliente
     */
}