package com.servico.retry.infra.consumer;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servico.retry.DTO.DeviceRetryConsumerDTO;
import com.servico.retry.service.DeviceRetryService;

@Service
public class DeviceRetryConsumer {

	private final Logger logger = LoggerFactory.getLogger(DeviceRetryConsumer.class);
	
	@Autowired
	private DeviceRetryService deviceRetryService;
	

	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${retry.tempo.tentativa}")
	private Integer tempoTentativa;

	@KafkaListener(topics = "${topic.device.retry}", groupId = "group_id")
	public void consume(ConsumerRecord<String, String> payload) throws IOException, InterruptedException {
		Thread.sleep(tempoTentativa);
		logger.info("key: {}", payload.key());
		logger.info("Headers: {}", payload.headers());
		logger.info("Partion: {}", payload.partition());
		logger.info("Order: {}", payload.value());
		
		for(Header header : payload.headers()) {
			if(header.key().equals("attempt-counter")) {
				DeviceRetryConsumerDTO deviceContractedConsumerDTO= objectMapper.readValue(payload.value(), DeviceRetryConsumerDTO.class);
				deviceRetryService.cadastrar(deviceContractedConsumerDTO,Integer.parseInt(new String(header.value())));
			}
		}
		
		
	}

}
