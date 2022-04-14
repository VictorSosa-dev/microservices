package com.carro.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carro.service.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer>{
	
	List<Carro> findByUsuarioId(int usuarioId);

}
