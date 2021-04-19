package com.brikton.labapps.mspedido.service.impl;

import com.brikton.labapps.mspedido.domain.Obra;
import com.brikton.labapps.mspedido.domain.Pedido;
import com.brikton.labapps.mspedido.domain.EstadoPedido;
import com.brikton.labapps.mspedido.domain.Material;
import com.brikton.labapps.mspedido.exception.RiesgoException;
import com.brikton.labapps.mspedido.repository.PedidoRepository;
import com.brikton.labapps.mspedido.service.ClienteService;
import com.brikton.labapps.mspedido.service.MaterialService;
import com.brikton.labapps.mspedido.service.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	MaterialService materialSrv;
	
	@Autowired
	PedidoRepository repo;
	
	@Autowired
	ClienteService clienteSrv;

	
	@Override
	public Pedido crearPedido(Pedido p) throws RiesgoException {
		boolean hayStock = p.getDetalle()
		.stream()
		.allMatch(dp -> verificarStock(dp.getMaterial(),dp.getCantidad()));
		
		Double totalOrden = p.getDetalle()
				.stream()
				.mapToDouble( dp -> dp.getCantidad() * dp.getPrecio())
				.sum();
		
		
		Double saldoCliente = clienteSrv.deudaCliente(p.getObra());		
		Double nuevoSaldo = saldoCliente - totalOrden;
		
		Boolean generaDeuda= nuevoSaldo<0;
		if(hayStock ) {
				if(!generaDeuda || (generaDeuda && this.esDeBajoRiesgo(p.getObra(),nuevoSaldo) ))  {
					p.setEstado(new EstadoPedido(1,"ACEPTADO"));
				} else {
					throw new RiesgoException("riesgo");
				}
		} else {
			p.setEstado(new EstadoPedido(2,"PENDIENTE"));
		}
		return this.repo.save(p);
	}
	
	
	boolean verificarStock(Material m,Integer cantidad) {
		return materialSrv.stockDisponible(m)>=cantidad;
	}

	boolean esDeBajoRiesgo(Obra o,Double saldoNuevo) {
		Double maximoSaldoNegativo = clienteSrv.maximoSaldoNegativo(o);
		Boolean tieneSaldo = Math.abs(saldoNuevo) < maximoSaldoNegativo;
		return tieneSaldo;
	}

}