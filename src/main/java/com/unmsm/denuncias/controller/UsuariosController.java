package com.unmsm.denuncias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.denuncias.entity.Denuncia;
import com.unmsm.denuncias.entity.Usuario;
import com.unmsm.denuncias.service.impl.UsuarioServiceImpl;
import com.unmsm.denuncias.util.DenunciasUtil;
import com.unmsm.denuncias.util.Respuesta;

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
	
	
	@SuppressWarnings("unused")
	@CrossOrigin(origins = "*")
	@PostMapping("/usuarios/registrarUsuario")
	public Respuesta registrarUsuario(@RequestBody Usuario usuario) {
		System.out.println("Usuario a registrar: "+usuario.toString());
		
		Respuesta respuesta = new Respuesta();
		
		if(usuario != null) {
			usuarioServiceImpl.addUsuario(usuario);
			respuesta.setCodigo("OK");
			respuesta.setMensaje("Usuario registrado");
			respuesta.setObject(DenunciasUtil.toJson(usuario));
		} else {
			respuesta.setCodigo("!OK");
			respuesta.setMensaje("No se registró el usuario");
			respuesta.setObject(null);
		}
		
		return respuesta;
	}
	
}
