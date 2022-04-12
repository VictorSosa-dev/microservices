package com.user.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.model.User;
import com.user.service.modelos.Carro;
import com.user.service.modelos.Moto;
import com.user.service.servicio.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> userList(){
		List<User> users = userService.getAll();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") int id){
		User user = userService.getUserById(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		User newUser = userService.save(user);
		
		return ResponseEntity.ok(newUser);
	}
	
	@GetMapping("/carros/{userId}")
	public ResponseEntity<List<Carro>> getCarros(@PathVariable("userId") int id){
		User user = userService.getUserById(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		List<Carro> carros = userService.getCarros(id);
		return ResponseEntity.ok(carros);
	}
	
	@GetMapping("/motos/{userId}")
	public ResponseEntity<List<Moto>> getMotos(@PathVariable("userId") int id){
		User user = userService.getUserById(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motos = userService.getMotos(id);
		return ResponseEntity.ok(motos);
	}
	
	@PostMapping("/carro/{userId}")
	public ResponseEntity<Carro> guardarCarro(@PathVariable("userId")int userId, @RequestBody Carro carro ){
		Carro nuevoCarro  = userService.saveCarro(userId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}
	
	@PostMapping("/moto/{userId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("userId")int userId, @RequestBody Moto moto ){
		Moto nuevaMoto  = userService.saveMoto(userId, moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/todos/{userId}")
	public ResponseEntity<Map<String, Object>> listarTodosVehiculos(@PathVariable("userId")int userId){
		Map<String,Object> resultado = userService.getUserAutos(userId);
		return ResponseEntity.ok(resultado);
	}	
	
}
