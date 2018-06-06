package com.unmsm.denuncias.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.unmsm.denuncias.entity.Denuncia;

@Controller
public class NotificacionesController {
	
	@CrossOrigin(origins = "*")
	@GetMapping("/notificaciones/sendWhatsapp")
	public void sendWhatsapp() {
		
		
		
	}
	
}
