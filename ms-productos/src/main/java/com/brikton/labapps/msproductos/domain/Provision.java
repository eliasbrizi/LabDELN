package com.brikton.labapps.msproductos.domain;


import java.time.LocalDate;
import java.util.List;

public class Provision {

	private Integer id;
	private LocalDate fechaProvision;
	private List<DetalleProvision> detalle;
	
	public Provision(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFechaProvision() {
		return fechaProvision;
	}
	public void setFechaProvision(LocalDate fechaProvision) {
		this.fechaProvision = fechaProvision;
	}
	public List<DetalleProvision> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DetalleProvision> detalle) {
		this.detalle = detalle;
	}

	
}
