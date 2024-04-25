package com.servico.principal.DTO;

import java.time.LocalDate;

public class DeviceContractedConsumerDTO {
	
	private String device;
	private LocalDate releaseData;
	private String clientName;
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public LocalDate getReleaseData() {
		return releaseData;
	}
	public void setReleaseData(LocalDate releaseData) {
		this.releaseData = releaseData;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	

}
