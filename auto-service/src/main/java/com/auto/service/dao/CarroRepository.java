package com.auto.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auto.service.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer>{
	
	List<Carro> findByUserId(int userId);

}
