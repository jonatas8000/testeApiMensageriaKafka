package com.rest.testeCadastral.DTO;

import jakarta.validation.constraints.NotBlank;

public class DeviceContractedDTO {
	
	@NotBlank
	private String device;
	
	private Boolean released;

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Boolean getReleased() {
		return released;
	}

	public void setReleased(Boolean released) {
		this.released = released;
	}

	
	
	

}
