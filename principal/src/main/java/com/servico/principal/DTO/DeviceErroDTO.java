package com.servico.principal.DTO;

public class DeviceErroDTO {
	
	private String device;
	private TipoErro errorEnum;
	private String errorException;
	
	
	public DeviceErroDTO(String device, TipoErro errorEnum, String errorException) {
		this.device = device;
		this.errorEnum = errorEnum;
		this.errorException = errorException;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public TipoErro getErrorEnum() {
		return errorEnum;
	}
	public void setErrorEnum(TipoErro errorEnum) {
		this.errorEnum = errorEnum;
	}
	public String getErrorException() {
		return errorException;
	}
	public void setErrorException(String errorException) {
		this.errorException = errorException;
	}
	
	

}
