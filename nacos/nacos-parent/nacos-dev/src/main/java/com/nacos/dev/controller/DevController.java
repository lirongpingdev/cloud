package com.nacos.dev.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
@RefreshScope //自动刷新
public class DevController {
	
	//@Value("${service.name}")//不会自动刷新
	@Value("${service.name:false}") //自动刷新
	private String name;
	
	@GetMapping(value="/name")
	public String getName() {
		return name;
	}
}
