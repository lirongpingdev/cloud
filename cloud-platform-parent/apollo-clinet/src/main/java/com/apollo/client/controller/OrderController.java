package com.apollo.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

/**
 * @EnableApolloConfig默认取application
 *
 */
@RestController	
@EnableApolloConfig
public class OrderController {

	@Value("${jdbc.url}")
	private String jdbcurl;
	
	@Value("${application.name}")
	private String applicationName;
	
	@RequestMapping(value = "/getStr", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getStr() {
		return "-----------applicationName="+applicationName+",===jdbcurl=="+jdbcurl;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getProperties() {
		return "test ok!!";
	}
}
