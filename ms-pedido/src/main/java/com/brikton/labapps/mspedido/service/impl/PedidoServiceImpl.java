package com.brikton.labapps.msstock.service.impl;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	MaterialService materialSrv;
	
	@Autowired
	PedidoRepository repo;
	
	@Autowired
	ClienteService clienteSrv;

	
	@Override
	public Pedido crearPedido(Pedido p) {
		System.out.println("HOLA PEDIDO "+p);
		boolean hayStock = p.getDetalle()
		.stream()
		.allMatch(dp -> verificarStock(dp.getProducto(),dp.getCantidad()));
		
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
					throw new RuntimeException("No tiene aprobacion crediticia");
				}
		} else {
			p.setEstado(new EstadoPedido(2,"PENDIENTE"));
		}
		return this.repo.save(p);
	}
	
	
	boolean verificarStock(Producto p,Integer cantidad) {
		return materialSrv.stockDisponible(p)>=cantidad;
	}

	boolean esDeBajoRiesgo(Obra o,Double saldoNuevo) {
		Double maximoSaldoNegativo = clienteSrv.maximoSaldoNegativo(o);
		Boolean tieneSaldo = Math.abs(saldoNuevo) < maximoSaldoNegativo;
		return tieneSaldo;
	}

}