package com.brikton.labapps.mscuentacorriente.domain;

public abstract class MedioPago {

	protected Integer id;
	protected String observacion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}