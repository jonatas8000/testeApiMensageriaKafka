package com.rest.testeCadastral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.testeCadastral.DTO.DeviceContractedDTO;
import com.rest.testeCadastral.entity.DeviceEntity;
import com.rest.testeCadastral.entity.ReleaseUseEntity;
import com.rest.testeCadastral.entity.ReleaseUseEnum;
import com.rest.testeCadastral.exception.ErroServidorException;
import com.rest.testeCadastral.repository.DeviceRespository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeviceService {
	
	@Autowired
	private DeviceRespository devicesRespository;
	
	
	public void cadastrar(DeviceContractedDTO deviceContractedDTO) {
		try {
		devicesRespository.save(new DeviceEntity(deviceContractedDTO.getDevice(), 
				new ReleaseUseEntity(this.buscarReleaseUse(deviceContractedDTO.getReleased()))));
		}catch (Exception e) {
			throw new ErroServidorException("Erro ao salvar device"); 
		}
	}

	
	private ReleaseUseEnum buscarReleaseUse(Boolean release) {
		if(release==null)
			return ReleaseUseEnum.ITEM_NAO_PROCESSADO;
		else if(release)
			return ReleaseUseEnum.RELEASED;
		else
			return ReleaseUseEnum.NOT_RELEASED;
			
	}
}
