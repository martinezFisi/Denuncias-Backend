package com.unmsm.denuncias.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name="denuncia", uniqueConstraints = @UniqueConstraint( columnNames = {"codigo", "dni"} ))
public class Denuncia {

	@Id
	@Column(name="codigo", unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;

	@Column(name="direccion")
	private String direccion;

	@Column(name="latitud")
	private float latitud;

	@Column(name="longitud")
	private float longitud;

	@Column(name="categoria")
	private String categoria;

	@Column(name="comentario")
	private String comentario;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="dni", nullable=false)
	private Usuario usuario;

	public Denuncia(int codigo, String direccion, float latitud, float longitud, String categoria, String comentario,
			Usuario usuario) {
		super();
		this.codigo = codigo;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.categoria = categoria;
		this.comentario = comentario;
		this.usuario = usuario;
	}

	public Denuncia() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}