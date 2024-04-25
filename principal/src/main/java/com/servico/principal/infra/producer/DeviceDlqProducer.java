package com.servico.principal.infra.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servico.principal.DTO.DeviceErroDTO;

@Service
public class DeviceDlqProducer extends DeviceErroProducer{

	private static final Logger logger = LoggerFactory.getLogger(DeviceRetryProducer.class);

	@Value("${topic.device.dlq}")
	private String topicDeviceDlq;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public void sendMessage(DeviceErroDTO deviceErroDTO) {
		logger.info("Mensagem -> {}", deviceErroDTO);
		this.kafkaTemplate.send(this.sendMessage(deviceErroDTO, topicDeviceDlq, objectMapper));
	}

}
