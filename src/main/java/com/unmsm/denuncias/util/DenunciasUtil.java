package com.unmsm.denuncias.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DenunciasUtil {
	
	
	
	public static String toJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return "";
		}
	}
	
	
	public static LocalDate stringToLocalDate(String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(fecha, formatter);
	}
	
	public static String mapearCategoria(String cat) {
		String categoria = "";
		
		switch(cat){
    	case "RMA":
    			categoria = "Robo a mano armada";
    		break;
    	case "RAV":
    			categoria = "Robo a vehiculo";
    		break;
    	case "VAN":
    			categoria = "Vandalismo";
    		break;
    	case "RAP":
    			categoria = "Robo al paso";
    		break;
    	default:
    			categoria = "Sin categoria";
    		break;
		}
		
		return categoria;
	}
	
}
