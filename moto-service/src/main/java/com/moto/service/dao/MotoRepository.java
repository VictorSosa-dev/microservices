package com.moto.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moto.service.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer>{
	List<Moto> findByUsuarioId(int usuarioId); 

}
