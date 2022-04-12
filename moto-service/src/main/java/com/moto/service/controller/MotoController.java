package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.model.Moto;
import com.moto.service.servicio.MotoService;


@RestController
@RequestMapping("/moto")
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos(){
		List<Moto> Motos = motoService.getAll();
		if(Motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Motos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> getById(@PathVariable("id") int id){
		Moto nuevaMoto = motoService.getMotoById(id);
		if(nuevaMoto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@PostMapping
	public ResponseEntity<Moto> save(@RequestBody Moto moto){
		Moto nuevoMoto = motoService.save(moto);
		
		return ResponseEntity.ok(nuevoMoto);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Moto>> listarMotosPorUsuarioID(@PathVariable("userId") int id){
		List<Moto> motos = motoService.byUserId(id);
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);

		
	}
}
