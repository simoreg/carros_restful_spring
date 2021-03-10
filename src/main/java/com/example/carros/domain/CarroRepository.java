package com.example.carros.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface CarroRepository extends RevisionRepository<Carro, Long, Long>, JpaRepository<Carro, Long> {

	List<Carro> findByTipo(String tipo);

}
