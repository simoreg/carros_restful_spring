package com.example.carros.domain;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.example.listener.AuditListener;

import lombok.Data;

@Data
@Entity(name="revinfo")
@RevisionEntity(AuditListener.class)
public class AuditEntity extends DefaultRevisionEntity {
	
	private static final long serialVersionUID = 1L;
	
	public String usuario;
	public String cpf;
  
}
