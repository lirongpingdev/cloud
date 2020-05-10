package com.nacos.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @GetMapping("/service")
    public String service() {
	return "call producer service at="+System.currentTimeMillis();
    }

}
