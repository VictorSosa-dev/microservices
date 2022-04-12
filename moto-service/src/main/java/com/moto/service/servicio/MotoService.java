package com.moto.service.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.dao.MotoRepository;
import com.moto.service.model.Moto;


@Service
public class MotoService {
	
	@Autowired
	private MotoRepository motoRepository;
	
	public List<Moto> getAll(){
		return motoRepository.findAll();
	}
	
	public Moto getMotoById(int id) {
		return motoRepository.findById(id).orElse(null);
	}
	
	public Moto save(Moto Moto) {
		Moto nuevoMoto= motoRepository.save(Moto);
		return nuevoMoto;
	}
	
	public List<Moto> byUserId(int id){
		return motoRepository.findByUserId(id);
	}
}
