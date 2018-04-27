package com.unmsm.denuncias.service;

import java.util.List;
import java.util.Optional;

import com.unmsm.denuncias.entity.Denuncia;

public interface DenunciaService {
	
	public Denuncia addDenuncia(Denuncia denuncia);
	public List<Denuncia> listAllDenuncias();
	public Denuncia findDenunciaById(int id);
	public String removeDenuncia(int id);
	public String getMessage();

}
