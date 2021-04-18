package com.brikton.labapps.mspedido.service;

import com.brikton.labapps.mspedido.domain.Pedido;
import com.brikton.labapps.mspedido.exception.RiesgoException;

public interface PedidoService {

	public Pedido crearPedido(Pedido p) throws RiesgoException;
}