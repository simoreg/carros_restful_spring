package com.example.carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	@Autowired
	private CarroService service;
	
	@GetMapping()
	public ResponseEntity get() {
		return new ResponseEntity<List<CarroDTO>>(service.getCarros(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getCarroById(@PathVariable("id") Long id){
		Optional<CarroDTO> carro = service.getCarroById(id);
		return carro.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo){
		List<CarroDTO> carros = service.getCarroByTipo(tipo);
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
	}
	
	@PostMapping
	public ResponseEntity post(@RequestBody Carro carro) {
		try {
			CarroDTO carroDTO = service.insert(carro);
			return ResponseEntity.created(getUri(carroDTO.getId())).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity put(@RequestBody Carro carro, @PathVariable("id") Long id) {
		CarroDTO carroDTO = service.update(carro,id);
		return carroDTO != null ?
				ResponseEntity.ok(carroDTO) :
					ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = service.delete(id);
		return ok ?
				ResponseEntity.ok().build() :
					ResponseEntity.notFound().build();
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
	
}
