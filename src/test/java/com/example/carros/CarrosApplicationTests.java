package com.example.carros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.dto.CarroDTO;


@SpringBootTest
class CarrosApplicationTests {
	
	@Autowired
	private CarroService service;

	@Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

       // Insert
       CarroDTO c =  service.insert(carro);
       
       Long id = c.getId();
       assertNotNull(c);
       
       Optional<CarroDTO> op = service.getCarroById(id);
       assertTrue(op.isPresent());
       
       c = op.get();
       assertEquals("Porshe", c.getNome());
       assertEquals("esportivos", c.getTipo());
       
       service.delete(id);
       
       assertFalse(service.getCarroById(id).isPresent());
    }
	
	@Test
	public void testLista() {
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(30, carros.size());
	}
	
	@Test
	public void testGet() {
		Optional<CarroDTO> op = service.getCarroById(11L);
		
		assertTrue(op.isPresent());
		
		CarroDTO c = op.get();
		
		assertEquals("Ferrari FF", c.getNome());
		
	}

}
