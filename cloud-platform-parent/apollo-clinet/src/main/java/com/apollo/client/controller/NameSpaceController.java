package com.apollo.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@RestController	
@EnableApolloConfig("wowo")
public class NameSpaceController {

	@Value("${com.domain.cn}")
	private String com_domain_cn;
	
	@RequestMapping(value = "/getdomain", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getStr() {
		return "domain:"+com_domain_cn;
	}
}
