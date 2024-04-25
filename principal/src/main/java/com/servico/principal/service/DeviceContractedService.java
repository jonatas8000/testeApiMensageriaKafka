package com.servico.principal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servico.principal.DTO.DeviceContractedConsumerDTO;
import com.servico.principal.DTO.DeviceContractedDTO;
import com.servico.principal.DTO.DeviceErroDTO;
import com.servico.principal.DTO.ErroDTO;
import com.servico.principal.DTO.TipoErro;
import com.servico.principal.entity.DeviceEntity;
import com.servico.principal.entity.ReleaseUseEntity;
import com.servico.principal.entity.ReleaseUseEnum;
import com.servico.principal.infra.producer.DeviceDlqProducer;
import com.servico.principal.infra.producer.DeviceRetryProducer;
import com.servico.principal.repository.DeviceRespository;

@Service
public class DeviceContractedService {

	@Value("${rest.host}")
	private String url;

	@Autowired
	private DeviceRespository deviceRespository;

	@Autowired
	private DeviceRetryProducer deviceRetryProducer;

	@Autowired
	private DeviceDlqProducer deviceDlqProducer;

	@Autowired
	private ObjectMapper objectMapper;

	public void cadastrar(DeviceContractedConsumerDTO deviceContractedConsumerDTO) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			ResponseEntity<Void> response = restTemplate.postForEntity(url + "/device/contracted",
					new DeviceContractedDTO(deviceContractedConsumerDTO.getDevice(), true), null);

			if (response.getStatusCode().is2xxSuccessful())
				this.atualizar(deviceContractedConsumerDTO);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().is5xxServerError())
				this.gerarMensagemRetry(e.getResponseBodyAsString(), deviceContractedConsumerDTO.getDevice());
			else if (e.getStatusCode().is4xxClientError())
				this.gerarMensagemDlq(e.getResponseBodyAsString(), deviceContractedConsumerDTO.getDevice());

		} catch (Exception e) {
			deviceRetryProducer.sendMessage(
					new DeviceErroDTO(deviceContractedConsumerDTO.getDevice(), TipoErro.APIERROR, e.getMessage()));
		}

	}

	private void gerarMensagemRetry(String erro, String device) {
		try {
			ErroDTO erroDTO = objectMapper.readValue(erro, ErroDTO.class);
			deviceRetryProducer.sendMessage(new DeviceErroDTO(device, TipoErro.APIERROR, erroDTO.getErrorException()));
		} catch (JsonProcessingException e1) {
			deviceRetryProducer.sendMessage(new DeviceErroDTO(device, TipoErro.APIERROR, "JsonProcessingException"));
		}
	}

	private void gerarMensagemDlq(String erro, String device) {
		try {
			ErroDTO erroDTO = objectMapper.readValue(erro, ErroDTO.class);
			deviceDlqProducer.sendMessage(new DeviceErroDTO(device, TipoErro.APIERROR, erroDTO.getErrorException()));
		} catch (JsonProcessingException e1) {
			deviceDlqProducer.sendMessage(new DeviceErroDTO(device, TipoErro.APIERROR, "JsonProcessingException"));
		}
	}

	private void atualizar(DeviceContractedConsumerDTO deviceContractedConsumerDTO) {
		try {
			deviceRespository.save(new DeviceEntity(deviceContractedConsumerDTO.getDevice(),
					deviceContractedConsumerDTO.getClientName(), deviceContractedConsumerDTO.getReleaseData(),
					new ReleaseUseEntity(ReleaseUseEnum.RELEASED)));
		} catch (Exception e) {
			deviceRetryProducer.sendMessage(
					new DeviceErroDTO(deviceContractedConsumerDTO.getDevice(), TipoErro.APIERROR, e.getMessage()));
		}
	}

}
