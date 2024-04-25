package com.servico.retry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servico.retry.DTO.DeviceErroDTO;
import com.servico.retry.DTO.DeviceRetryConsumerDTO;
import com.servico.retry.DTO.DeviceRetryDTO;
import com.servico.retry.DTO.ErroDTO;
import com.servico.retry.DTO.TipoErro;
import com.servico.retry.infra.producer.DeviceDlqProducer;
import com.servico.retry.infra.producer.DeviceRetryProducer;

@Service
public class DeviceRetryService {

	@Value("${rest.host}")
	private String url;

	@Autowired
	private DeviceRetryProducer deviceRetryProducer;

	@Autowired
	private DeviceDlqProducer deviceDlqProducer;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${retry.quantidade.tentativa}")
	private Integer numeroTentativa;

	public void cadastrar(DeviceRetryConsumerDTO deviceRetryConsumerDTO,int tentativa) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			restTemplate.postForEntity(url + "/device/contracted",
					new DeviceRetryDTO(deviceRetryConsumerDTO.getDevice(), true), null);

		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().is5xxServerError())
				this.gerarMensagemRetry(e.getResponseBodyAsString(), deviceRetryConsumerDTO.getDevice(),tentativa);
			else if (e.getStatusCode().is4xxClientError())
				this.gerarMensagemDlq(e.getResponseBodyAsString(), deviceRetryConsumerDTO.getDevice(),tentativa);

		} catch (Exception e) {
			if(tentativa<numeroTentativa) {
			deviceRetryProducer.sendMessage(
					new DeviceErroDTO(deviceRetryConsumerDTO.getDevice(), TipoErro.APIERROR, e.getMessage()),++tentativa);
			}else {
				deviceDlqProducer.sendMessage(
						new DeviceErroDTO(deviceRetryConsumerDTO.getDevice(), TipoErro.APIERROR, e.getMessage()),tentativa);
			}
		}

	}

	private void gerarMensagemRetry(String erro, String device, int tentativa) {
		try {
			if(tentativa<numeroTentativa) {
			ErroDTO erroDTO = objectMapper.readValue(erro, ErroDTO.class);
			deviceRetryProducer.sendMessage(new DeviceErroDTO(device, TipoErro.APIERROR, erroDTO.getErrorException()),++tentativa);
			}else {
				this.gerarMensagemDlq(erro, device,tentativa);
			}
		} catch (JsonProcessingException e1) {
			deviceRetryProducer.sendMessage(new DeviceErroDTO(device, TipoErro.APIERROR, "JsonProcessingException"),++tentativa);
		}
	}

	private void gerarMensagemDlq(String erro, String device,int tentativa) {
		try {
			ErroDTO erroDTO = objectMapper.readValue(erro, ErroDTO.class);
			deviceDlqProducer.sendMessage(new DeviceErroDTO(device, TipoErro.APIERROR, erroDTO.getErrorException()),tentativa);
		} catch (JsonProcessingException e1) {
			deviceDlqProducer.sendMessage(new DeviceErroDTO(device, TipoErro.APIERROR, "JsonProcessingException"),tentativa);
		}
	}


}
