package com.rest.testeCadastral.entity;

public enum ReleaseUseEnum {

	ITEM_NAO_PROCESSADO (1),
	RELEASED (2),
	NOT_RELEASED(3);
	
	private Integer id;
	
		
	private ReleaseUseEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	

}
