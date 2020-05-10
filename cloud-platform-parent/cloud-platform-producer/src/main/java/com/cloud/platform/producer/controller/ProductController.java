package com.cloud.platform.producer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.platform.producer.bean.User;

@RestController	
@RequestMapping("/product")
public class ProductController {
	@RequestMapping(value = "/getByIdUseLoadBalancedRestTemplate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getByIdUseLoadBalancedRestTemplate(@PathVariable Integer id) {
		System.out.println("getByIdUseLoadBalancedRestTemplate===============");
		return "getByIdUseLoadBalancedRestTemplate MSG!id="+id;
	}
	
	@RequestMapping(value = "/noLoadBalancedRestTemplate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String noLoadBalancedRestTemplate(@PathVariable Integer id) {
		System.out.println("noLoadBalancedRestTemplate===============");
		return "noLoadBalancedRestTemplate MSG!id="+id;
	}
	
	@RequestMapping(value = "/getByIdUseFeignClient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getByIdUseFeignClient(@PathVariable Integer id) {
		System.out.println("getByIdUseFeignClient===============");
		return "getByIdUseFeignClient MSG!id="+id;
	}
	
	@RequestMapping(value = "/loadBalancedClient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String loadBalancedClient(@PathVariable Integer id) {
		System.out.println("loadBalancedClient===============");
		return "loadBalancedClient MSG!id="+id;
	}
	
	@RequestMapping(value = "/ribbonLoadBalancerClient/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String ribbonLoadBalancerClient(@PathVariable Integer id) {
		System.out.println("ribbonLoadBalancerClient===============");
		return "ribbonLoadBalancerClient MSG!id="+id;
	}
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserList() {
		System.out.println("getUserList===============");
		List<User> list = new ArrayList<User>();
		for(int i=0;i<10;i++) {
			list.add(new User(100+i, "userName"+i, "100000000"+i, "address"+i) );
		}
		return list;
	}
}
