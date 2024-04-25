package com.rest.testeCadastral.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DEVICES",schema = "PROD")
public class DeviceEntity {

	@Id
	private String device;
	
	private String clientName;
	
	private LocalDate releaseDate;
	
	@ManyToOne
	@JoinColumn(name ="ID_RELEASE_USE")
	private ReleaseUseEntity releaseUseEntity;
	
	

	public DeviceEntity() {
	}

	public DeviceEntity(String device, ReleaseUseEntity releaseUseEntity) {
		this.device = device;
		this.releaseUseEntity = releaseUseEntity;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public ReleaseUseEntity getReleaseUseEntity() {
		return releaseUseEntity;
	}

	public void setReleaseUseEntity(ReleaseUseEntity releaseUseEntity) {
		this.releaseUseEntity = releaseUseEntity;
	}
	
	
	
}
