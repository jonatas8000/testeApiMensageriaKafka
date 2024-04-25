package com.servico.principal.DTO;

public class DeviceContractedDTO {
		
	private String device;
	
	private Boolean released;
	
	
	public DeviceContractedDTO(String device, Boolean released) {
		this.device = device;
		this.released = released;
	}

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
