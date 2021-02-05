package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.carros.dto.CarroDTO;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;

	public List<CarroDTO> getCarros(){
		return repository.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
	}
	
	public Optional<CarroDTO> getCarroById(Long id){
		return repository.findById(id).map(CarroDTO::create);
	}
	
	public List<Carro> getCarrosFake(){
		ArrayList<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Chevette"));
		carros.add(new Carro(3L, "Corolla"));
		
		return carros;
	}

	public List<CarroDTO> getCarroByTipo(String tipo) {
		return repository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro!");
		return CarroDTO.create(repository.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		
		Assert.notNull(id, "Não foi possível atualizar o registro!");
		
		Optional<Carro> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			repository.save(db);
			
			return CarroDTO.create(db);
			
		}else {
			return null;
		}

	}

	public boolean delete(Long id) {
		if(getCarroById(id).isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

}
