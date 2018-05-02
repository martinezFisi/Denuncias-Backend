package com.unmsm.denuncias.controller;

import static org.mockito.Mockito.after;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.denuncias.entity.Denuncia;
import com.unmsm.denuncias.entity.Usuario;
import com.unmsm.denuncias.service.impl.DenunciaServiceImpl;
import com.unmsm.denuncias.util.DenunciasUtil;
import com.unmsm.denuncias.util.Respuesta;

@RestController
public class DenunciasController {
	
	@Autowired
	@Qualifier("denunciaServiceImpl")
	private DenunciaServiceImpl denunciaServiceImpl;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/denuncias/registrarDenuncia")
	public Respuesta registrarDenuncia(@RequestBody Denuncia den) {

		den.setUsuario( new Usuario("73253818", "amartinez", "123456", "Antony", 
									"Martinez", 24, "antonymartinez12@gmail.com", "admin" ) );
		
		
		System.out.println("Denuncia a registrar: "+den.toString());
		
		Denuncia denuncia = denunciaServiceImpl.addDenuncia(den);
		
		Respuesta respuesta = new Respuesta();
		
		if(denuncia != null){
			respuesta.setCodigo("OK");
			respuesta.setMensaje("Denuncia registrada");
			respuesta.setObject(DenunciasUtil.toJson(denuncia));
		}else {
			respuesta.setCodigo("!OK");
			respuesta.setMensaje("No se registró la denuncia");
			respuesta.setObject(null);
		}
		
		return respuesta;
	}
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/denuncias/listarDenuncias")
	public List<Denuncia> listarDenuncias() {
		List<Denuncia> list;
		list = denunciaServiceImpl.listAllDenuncias();
		return list;
	}
	
}
