package com.apollo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApolloApp {
	public static void main(String[] args) {
		SpringApplication.run(ApolloApp.class, args);
	}
}
