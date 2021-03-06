package com.unmsm.denuncias.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@Column(name="dni")
	private String dni;
	
	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="nombres")
	private String nombres;

	@Column(name="apellidos")
	private String apellidos;

	@Column(name="edad")
	private int edad;

	@Column(name="correo")
	private String correo;

	@Column(name="tipo_usuario")
	private String tipoUsuario;
	
	@Column(name="celular")
	private String celular;

	@Column(name="latitud",precision=15, scale=20)
	private double latitud;

	@Column(name="longitud",precision=15, scale=20)
	private double longitud;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
	private Set<Denuncia> denunciasSet = new HashSet<Denuncia>();

	
	
	public Usuario(String dni, String username, String password, String nombres, String apellidos, int edad,
			String correo, String tipoUsuario, String celular, double latitud, double longitud) {
		super();
		this.dni = dni;
		this.username = username;
		this.password = password;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.correo = correo;
		this.tipoUsuario = tipoUsuario;
		this.celular = celular;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Usuario() {
		super();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", username=" + username + ", password=" + password + ", nombres=" + nombres
				+ ", apellidos=" + apellidos + ", edad=" + edad + ", correo=" + correo + ", tipoUsuario=" + tipoUsuario
				+ ", celular=" + celular + ", latitud=" + latitud + ", longitud=" + longitud + ", denunciasSet="
				+ denunciasSet + "]";
	}
	
	
	
}
