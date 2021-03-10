package com.example.carros.domain;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.Data;

@Data
@Entity
@Table(name="carro", schema="carros")
@Audited
public class Carro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String tipo;
	
	private String descricao;
	
    private String urlFoto;
    
    private String urlVideo;
    
    private String latitude;
   
    private String longitude;
	
	public Carro() {
		
	}
	
	public Carro(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

}
