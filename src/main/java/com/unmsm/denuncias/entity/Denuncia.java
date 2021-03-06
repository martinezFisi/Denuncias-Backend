package com.unmsm.denuncias.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.unmsm.denuncias.util.DenunciasUtil;



@Entity
@Table(name="denuncia", uniqueConstraints = @UniqueConstraint( columnNames = {"codigo", "dni"} ))
public class Denuncia {

	@Id
	@Column(name="codigo", unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int codigo;

	@Column(name="direccion")
	private String direccion;

	@Column(name="latitud",precision=15, scale=20)
	private double latitud;

	@Column(name="longitud",precision=15, scale=20)
	private double longitud;

	@Column(name="categoria")
	private String categoria;

	@Column(name="comentario")
	private String comentario;

	private LocalDate date;
	 
    private LocalTime time;

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
		this.date = LocalDate.now();
		this.time = LocalTime.now();
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

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate() {
		this.date = LocalDate.now();
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime() {
		this.time = LocalTime.now();
	}

	@Override
	public String toString() {
		return "Denuncia [codigo=" + codigo + ", direccion=" + direccion + ", latitud=" + latitud + ", longitud="
				+ longitud + ", categoria=" + categoria + ", comentario=" + comentario + ", date=" + date + ", time="
				+ time + ", usuario=" + usuario + "]";
	}
	
	public String toMessageWhatsapp() {
		return  "Se produjo un robo cerca de tu ubicación.  " +
				"*Denuncia Nro " + codigo + " / Categoria " + DenunciasUtil.mapearCategoria(categoria) + ". "
				+ "*Comentario: " + comentario + ". " 
				+ "*Ubicacion: " + direccion + ". "
				+ "*Denuncia registrada por el usuario " + usuario.getUsername() + " el " + date + " a las " + time + ".";
	}
	
	public String toMessageEmail() {
		return  "Se produjo un robo cerca de tu ubicación.  \n" +
				"*Denuncia Nro " + codigo + " / Categoria " + DenunciasUtil.mapearCategoria(categoria) + ". \n"
				+ "*Comentario: " + comentario + ". \n" 
				+ "*Ubicacion: " + direccion + ". \n"
				+ "*Denuncia registrada por el usuario " + usuario.getUsername() + " el " + date + " a las " + time + ".";
	}
	

	
	
}
