package com.unmsm.denuncias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unmsm.denuncias.entity.Usuario;
import com.unmsm.denuncias.repository.UsuarioJpaRepository;
import com.unmsm.denuncias.service.UsuarioService;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioJpaRepository")
	private UsuarioJpaRepository usuarioJpaRepository;

	private String message;

	@Override
	public Usuario addUsuario(Usuario usuario) {
		return usuarioJpaRepository.save(usuario);
	}

	@Override
	public List<Usuario> listAllUsuarios() {
		return usuarioJpaRepository.findAll();
	}

	@Override
	public Usuario findUsuarioById(String dni) {
		return usuarioJpaRepository.findById(dni).get();
	}

	@Override
	public String removeUsuario(String dni) {
		Usuario usuario = findUsuarioById(dni);

		if (usuario != null) {
			usuarioJpaRepository.delete(usuario);
		} else {
			message = "Id no encontrado";
		}
		return getMessage();
	}

	@Override
	public String getMessage() {
		return message;
	}

}
