package com.unmsm.denuncias.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unmsm.denuncias.entity.Denuncia;
import com.unmsm.denuncias.repository.DenunciasJpaRepository;
import com.unmsm.denuncias.service.DenunciaService;

@Service("denunciaServiceImpl")
public class DenunciaServiceImpl implements DenunciaService {

	@Autowired
	@Qualifier("denunciasJpaRepository")
	private DenunciasJpaRepository denunciasJpaRepository;
	
	private String message;
	
	@Override
	public Denuncia addDenuncia(Denuncia denuncia) {
		return denunciasJpaRepository.save(denuncia);
	}

	@Override
	public List<Denuncia> listAllDenuncias() { 
		return denunciasJpaRepository.findAll();
	}

	@Override
	public Denuncia findDenunciaById(int id) {
		return denunciasJpaRepository.findById(id).get();
	}

	@Override
	public String removeDenuncia(int id) {
		Denuncia denuncia = findDenunciaById(id);
		
		if(denuncia != null) {
			denunciasJpaRepository.delete(denuncia);
		} else {
			message = "Id no encontrado";
		}
		return getMessage();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
