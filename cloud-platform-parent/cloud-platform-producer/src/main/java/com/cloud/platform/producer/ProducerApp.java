package com.cloud.platform.producer;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ProducerApp {

	public static void main(String[] args) {
		//SpringApplication.run(EurekaApplication.class, args);
        new SpringApplicationBuilder(ProducerApp.class).web(WebApplicationType.SERVLET).run(args);
	}

}