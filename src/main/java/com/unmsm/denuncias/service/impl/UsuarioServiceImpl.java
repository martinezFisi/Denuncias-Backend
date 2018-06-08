package com.unmsm.denuncias.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.EntityManagerFactoryAccessor;
import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQuery;
import com.unmsm.denuncias.entity.QUsuario;
import com.unmsm.denuncias.entity.Usuario;
import com.unmsm.denuncias.repository.UsuarioJpaRepository;
import com.unmsm.denuncias.service.UsuarioService;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioJpaRepository")
	private UsuarioJpaRepository usuarioJpaRepository;

	private String message;
	
	@PersistenceContext
	EntityManager entityManager;

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

	@Override
	public Usuario findUsuarioByUsernameAndPassword(String userName, String password) {
		QUsuario usuario = QUsuario.usuario;
		JPAQuery<?> query = new JPAQuery<Void>(entityManager);
		Usuario usuarioAutenticado = 
				query.select(usuario).from(usuario).
				where(usuario.username.eq(userName).
					  and(usuario.password.eq(password)))
				.fetchOne();
		return usuarioAutenticado;
	}

}
