package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;

	public Iterable<Carro> getCarros(){
		return repository.findAll();
	}
	
	public List<Carro> getCarrosFake(){
		ArrayList<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Chevette"));
		carros.add(new Carro(3L, "Corolla"));
		
		return carros;
	}
}
