package com.servico.retry.DTO;

public class DeviceRetryConsumerDTO {
	
	private String device;
	private String errorEnum;
	private String errorException;
	
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getErrorEnum() {
		return errorEnum;
	}
	public void setErrorEnum(String errorEnum) {
		this.errorEnum = errorEnum;
	}
	public String getErrorException() {
		return errorException;
	}
	public void setErrorException(String errorException) {
		this.errorException = errorException;
	}
		
	

}
