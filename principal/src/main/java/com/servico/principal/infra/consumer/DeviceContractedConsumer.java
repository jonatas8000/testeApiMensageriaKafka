package com.servico.principal.infra.consumer;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servico.principal.DTO.DeviceContractedConsumerDTO;
import com.servico.principal.service.DeviceContractedService;

@Service
public class DeviceContractedConsumer {

	private final Logger logger = LoggerFactory.getLogger(DeviceContractedConsumer.class);
	
	@Autowired
	private DeviceContractedService deviceContractedService;
	

	@Autowired
	private ObjectMapper objectMapper;

	@KafkaListener(topics = "${topic.device.contracted}", groupId = "group_id")
	public void consume(ConsumerRecord<String, String> payload) throws IOException {
		logger.info("key: {}", payload.key());
		logger.info("Headers: {}", payload.headers());
		logger.info("Partion: {}", payload.partition());
		logger.info("Order: {}", payload.value());
		
		for(Header header : payload.headers()) {
			if(new String(header.value()).equals("device.contracted")) {
				DeviceContractedConsumerDTO deviceContractedConsumerDTO= objectMapper.readValue(payload.value(), DeviceContractedConsumerDTO.class);
				deviceContractedService.cadastrar(deviceContractedConsumerDTO);
			}
		}
		
		
	}

}
