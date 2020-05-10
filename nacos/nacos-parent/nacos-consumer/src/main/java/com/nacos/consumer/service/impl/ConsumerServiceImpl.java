package com.nacos.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nacos.consumer.client.ProducerClient;
import com.nacos.consumer.service.IConsumerService;

@Service
public class ConsumerServiceImpl implements IConsumerService{

    @Autowired
    private ProducerClient producerClient;
    
    @Override
    public String getProducerInfo() {
	return producerClient.service();
    }

}
