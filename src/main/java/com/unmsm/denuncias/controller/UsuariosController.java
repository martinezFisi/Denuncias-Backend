package com.unmsm.denuncias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.denuncias.entity.Usuario;
import com.unmsm.denuncias.service.impl.UsuarioServiceImpl;

@RestController
public class UsuariosController {

	@Autowired
	@Qualifier("usuarioServiceImpl")
	UsuarioServiceImpl usuarioServiceImpl;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/usuarios/login")
	public Usuario login(@RequestParam(name="userName") String userName,
						 @RequestParam(name="password") String password) {
		return usuarioServiceImpl.findUsuarioByUsernameAndPassword(userName, password);
	}
	
}
