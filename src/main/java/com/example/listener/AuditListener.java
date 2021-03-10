package com.example.listener;

import org.hibernate.envers.RevisionListener;

import com.example.carros.domain.AuditEntity;

public class AuditListener implements RevisionListener{
	
	@Override
    public void newRevision(Object revisionEntity) {    	
    	AuditEntity revEntity = (AuditEntity) revisionEntity;  
    	revEntity.setUsuario("Leandro");
    	revEntity.setCpf("123456789");
    }

}
