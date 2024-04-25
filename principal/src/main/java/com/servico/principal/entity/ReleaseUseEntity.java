package com.servico.principal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RELEASE_USE",schema = "PROD")
public class ReleaseUseEntity {
	
	@Id
    private Integer id;
    
    private String nome;
    
    
    
	public ReleaseUseEntity() {
		
	}

	public ReleaseUseEntity(ReleaseUseEnum releaseUseEnum) {
		this.id=releaseUseEnum.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
    

}
