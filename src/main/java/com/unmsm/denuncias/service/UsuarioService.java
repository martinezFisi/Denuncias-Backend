package com.unmsm.denuncias.service;

import java.util.List;

import com.unmsm.denuncias.entity.Usuario;

public interface UsuarioService {
	
	public Usuario addUsuario(Usuario usuario);
	public List<Usuario> listAllUsuarios();
	public Usuario findUsuarioById(String dni);
	public String removeUsuario(String dni);
	public String getMessage();
	
}
