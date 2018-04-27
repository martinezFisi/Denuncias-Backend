package com.unmsm.denuncias.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unmsm.denuncias.entity.Denuncia;

@Repository("denunciasJpaRepository")
public interface DenunciasJpaRepository extends JpaRepository<Denuncia, Serializable>{

	
	
}
