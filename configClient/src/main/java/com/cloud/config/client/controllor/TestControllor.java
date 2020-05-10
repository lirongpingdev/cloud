package com.cloud.config.client.controllor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController	
@RequestMapping("/client")
public class TestControllor {

	@Value("${jdbc.url}")
	private String jdbcurl;
	
	
	@RequestMapping(value = "/jdbcurl", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getjdbcurl() {
		return jdbcurl;
	}
		
}
