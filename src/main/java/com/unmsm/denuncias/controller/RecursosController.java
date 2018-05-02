package com.unmsm.denuncias.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.denuncias.model.Image;
import com.unmsm.denuncias.util.UtilBase64Image;

@RestController
public class RecursosController {

	@GetMapping("/recursos/icon")
	public Image get(@RequestParam("name") String name) {
		System.out.println(String.format("/GET info: imageName = %s", name));
		String imagePath = "C:\\imgs\\" + name + ".png";
		String imageBase64 = UtilBase64Image.encoder(imagePath);
		
		if(imageBase64 != null){
			Image image = new Image(name, imageBase64);
			return image;
		}
		return null;
	}
	
}
