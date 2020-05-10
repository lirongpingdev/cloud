package com.nacos.service8010.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class Service8010Controller {
	
	@Value("${common.config}")
	private String config;
	
	@GetMapping(value="/config")
	public String getConfig() {
		return config;
	}
}
