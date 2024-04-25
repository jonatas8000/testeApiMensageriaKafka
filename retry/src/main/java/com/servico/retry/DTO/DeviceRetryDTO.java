package com.servico.retry.DTO;

public class DeviceRetryDTO {
		
	private String device;
	
	private Boolean released;
	
	
	public DeviceRetryDTO(String device, Boolean released) {
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
