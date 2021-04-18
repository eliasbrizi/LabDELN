package com.brikton.labapps.mspedido.service;

import com.brikton.labapps.mspedido.domain.Obra;

public interface ClienteService {

	public Double deudaCliente(Obra id);
	public Double maximoSaldoNegativo(Obra id);
	public Integer situacionCrediticiaBCRA(Obra id);

}