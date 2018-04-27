package com.unmsm.denuncias.util;

public class Respuesta {

	String codigo;
	String mensaje;
	String object;

	public Respuesta(String codigo, String mensaje, String object) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public Respuesta() {
		super();
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}
