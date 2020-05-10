package com.cloud.eureka01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Server9001App {

	public static void main(String[] args) {
		SpringApplication.run(Server9001App.class, args);
	}

}
