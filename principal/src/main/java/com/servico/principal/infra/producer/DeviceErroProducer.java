package com.servico.principal.infra.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servico.principal.DTO.DeviceErroDTO;

public class DeviceErroProducer  {
	
	public ProducerRecord<String, String> sendMessage(DeviceErroDTO deviceErroDTO, String topico,ObjectMapper objectMapper) {
		String value=null;
		try {
			value = objectMapper.writeValueAsString(deviceErroDTO);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProducerRecord<String, String> record = new ProducerRecord<>(topico, deviceErroDTO.getDevice(), value);
		Header header = new RecordHeader("attempt-counter", "1".getBytes());
        record.headers().add(header);
		
       
		return record;
	}


}
