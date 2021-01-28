package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;

	public Iterable<Carro> getCarros(){
		return repository.findAll();
	}
	
	public Optional<Carro> getCarroById(Long id){
		return repository.findById(id);
	}
	
	public List<Carro> getCarrosFake(){
		ArrayList<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L, "Fusca"));
		carros.add(new Carro(2L, "Chevette"));
		carros.add(new Carro(3L, "Corolla"));
		
		return carros;
	}

	public Iterable<Carro> getCarroByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

	public Carro insert(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro!");
		return repository.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		
		Assert.notNull(id, "Não foi possível atualizar o registro!");
		
		Optional<Carro> optional = repository.findById(id);
		
		if(optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			repository.save(db);
			
			return db;
			
		}else {
			throw new RuntimeException("Não foi possível atualizar o registro!");
		}

	}

	public void delete(Long id) {
		if(getCarroById(id).isPresent()) {
			repository.deleteById(id);
		}
		
	}

}
