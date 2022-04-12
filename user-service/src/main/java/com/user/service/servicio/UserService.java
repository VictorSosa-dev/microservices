package com.user.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.dao.UserRepository;
import com.user.service.feignclients.CarroFeignClient;
import com.user.service.feignclients.MotoFeignClient;
import com.user.service.model.User;
import com.user.service.modelos.Carro;
import com.user.service.modelos.Moto;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarroFeignClient carroFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;

	// Rest Template
	public List<Carro> getCarros(int usuarioId) {
		List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/user/" + usuarioId, List.class);
		return carros;
	}

	public List<Moto> getMotos(int usuarioId) {
		List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/user/" + usuarioId, List.class);
		return motos;
	}

	// Feign Client
	public Carro saveCarro(int usuarioId, Carro carro) {
		carro.setUserId(usuarioId);
		Carro nuevoCarro = carroFeignClient.save(carro);
		return nuevoCarro;
	}
	
	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUserId(usuarioId);
		Moto nuevaMoto = motoFeignClient.save(moto);
		return nuevaMoto;
	}
	
	
	public Map<String,Object> getUserAutos(int userId){
		Map<String,Object> resultado = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
		
		resultado.put("Usuario", user);
		List<Carro> carros = carroFeignClient.getCarros(userId);
		if(carros.isEmpty()) {
			resultado.put("Carros", "El usuario no tiene carros");
		}else {
			resultado.put("Carros", carros);
		}
		
		List<Moto> motos = motoFeignClient.getMotos(userId);
		if(motos.isEmpty()) {
			resultado.put("Motos", "El usuario no tiene motos");
		}else {
			resultado.put("Motos", motos);
		}
		return resultado;
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}

}
