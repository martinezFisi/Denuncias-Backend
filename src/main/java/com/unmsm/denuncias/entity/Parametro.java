package com.unmsm.denuncias.entity;

import java.security.KeyStore.PrivateKeyEntry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parametro")
public class Parametro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_parametro")
	private int idParametro;
	
	@Column(name="parametro")
	private String parametro;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="descripcion")
	private String descripcion;

	public Parametro(int idParametro, String parametro, String codigo, String descripcion) {
		super();
		this.idParametro = idParametro;
		this.parametro = parametro;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Parametro() {
		super();
	}

	public int getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
