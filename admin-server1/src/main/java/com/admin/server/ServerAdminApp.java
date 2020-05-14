package com.admin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class ServerAdminApp {

	public static void main(String[] args) {
		SpringApplication.run(ServerAdminApp.class, args);
	}

}
