package com.brikton.labapps.msproductos.domain;

public class DetalleProvision {
	
	private Integer id;
	private Material material;
	private Integer cantidad;
	private Provision provision;

	public DetalleProvision(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}

	public Provision getProvision() {
		return provision;
	}

	public void setProvision(Provision provision) {
		this.provision = provision;
	}
	
	
	
}
