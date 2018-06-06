package com.unmsm.denuncias.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.denuncias.service.impl.DenunciaServiceImpl;
import com.unmsm.denuncias.service.impl.UsuarioServiceImpl;
import com.unmsm.denuncias.util.DenunciasUtil;
import com.unmsm.denuncias.util.EmailSender;
import com.unmsm.denuncias.util.Haversine;
import com.unmsm.denuncias.util.Respuesta;
import com.unmsm.denuncias.util.WhatsappSender;
import com.unmsm.denuncias.entity.*;

@RestController
public class DenunciasController {
	
	@Autowired
	@Qualifier("denunciaServiceImpl")
	private DenunciaServiceImpl denunciaServiceImpl;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/denuncias/registrarDenuncia")
	public Respuesta registrarDenuncia(@RequestBody Denuncia den) {
		
		den.setDate();
		den.setTime();
		
		System.out.println("Denuncia a registrar: "+den.toString());
		
		Denuncia denuncia = denunciaServiceImpl.addDenuncia(den);
		
		Respuesta respuesta = new Respuesta();
		
		if(denuncia != null){
			respuesta.setCodigo("OK");
			respuesta.setMensaje("Denuncia registrada");
			respuesta.setObject(DenunciasUtil.toJson(denuncia));
			
			List<Usuario> usuariosList = usuarioServiceImpl.listAllUsuarios();
			
			for(Usuario u : usuariosList) {
				if( Haversine.isNear(denuncia.getLatitud(), denuncia.getLongitud(), u.getLatitud(), u.getLongitud(), 30) ) {
					try {
						WhatsappSender.sendMessage( u.getCelular(), denuncia.toMessageWhatsapp());
						EmailSender.sendMail( u.getCorreo(), denuncia.toMessageEmail());
					} catch (Exception e) {
						System.out.println("No se pudo enviar el mensaje: " + e.getMessage());
					}
				}
			}
			
			
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
	
	@CrossOrigin(origins = "*")
	@GetMapping("/denuncias/buscarDenuncias")
	public List<Denuncia> buscarDenuncias(
			@RequestParam(name="latUser") double latitudUsuario,
			@RequestParam(name="lonUser") double longitudUsuario,
			@RequestParam(name="cat") String categoria,
			@RequestParam(name="fec") String fechaString,
			@RequestParam(name="rec") boolean recientes,
			@RequestParam(name="cerca") boolean cercaAMi) {
		
		System.out.println("latitudUsuario="+latitudUsuario);
		System.out.println("longitudUsuario="+longitudUsuario);
		System.out.println("Categoria="+categoria);
		System.out.println("recientes="+recientes);
		System.out.println("fecha=" + ( fechaString == "" || fechaString.equals(null) ? "" : DenunciasUtil.stringToLocalDate(fechaString) ) );
		System.out.println("cercaAMi="+cercaAMi);
		
		
		List<Denuncia> list = denunciaServiceImpl.listAllDenuncias();
		List<Denuncia> listFiltrado = new ArrayList<Denuncia>();
		
		for( Denuncia d : list ) {
			if( (categoria == null || categoria == "" ? true : d.getCategoria().equals(categoria) ) &&
				(fechaString == null || fechaString == "" ? true : d.getDate().equals(DenunciasUtil.stringToLocalDate(fechaString)) ) ) {
				
				if( cercaAMi ) {
					if( Haversine.isNear(latitudUsuario, longitudUsuario, d.getLatitud(), d.getLongitud(), 30) ) {
						listFiltrado.add(d);
					}
				}else {
					listFiltrado.add(d);
				}
				
				
			}
		}
		
		
		return listFiltrado;
	}
	
}
