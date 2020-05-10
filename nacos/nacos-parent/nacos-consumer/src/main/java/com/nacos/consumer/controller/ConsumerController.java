package com.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nacos.consumer.service.IConsumerService;

@RestController
public class ConsumerController {
    
    @Autowired
    private IConsumerService consumerService;
    
    @GetMapping("/getStr")
    public String getStr() {
	return "Consumer getStr()";
    }
    
    @GetMapping("/getProducerInfo")
    public String getProducerInfo() {
	return consumerService.getProducerInfo();
    }
}
