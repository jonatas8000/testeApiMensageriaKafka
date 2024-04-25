package com.rest.testeCadastral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.testeCadastral.DTO.DeviceContractedDTO;
import com.rest.testeCadastral.service.DeviceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/device/contracted", produces = {"application/json"})
public class DeviceContractedController {
	
	@Autowired
	private DeviceService deviceService;
	
	@PostMapping
	public ResponseEntity<Void>  cadastrar(@Valid @RequestBody DeviceContractedDTO deviceContractedDTO) {
		deviceService.cadastrar(deviceContractedDTO);
		return  new ResponseEntity<>(HttpStatus.CREATED);
	}

}
