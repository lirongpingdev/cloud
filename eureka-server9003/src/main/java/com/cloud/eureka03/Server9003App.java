package com.cloud.eureka03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Server9003App {
	public static void main(String[] args) {
		SpringApplication.run(Server9003App.class, args);
	}

}
