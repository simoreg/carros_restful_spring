package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;

public class CarroService {

	public List<Carro> getCarros(){
		ArrayList<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Chevette"));
		carros.add(new Carro(3L, "Corolla"));
		
		return carros;
	}
}
