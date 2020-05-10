package com.apollo.client.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apollo.client.bean.DevConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@RestController	
@EnableApolloConfig("wowo")
public class DevConfigController {
	
	@Resource
	private DevConfig devConfig;
	
	@RequestMapping(value = "/getDevConfig", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDevConfig() {
		return "DevConfig:"+devConfig.toString();
	}
}
