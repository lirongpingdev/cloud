package com.cloud.hystrix.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController	
@RequestMapping("/hystrix")
public class HyxtrixController {
	
	@HystrixCommand(fallbackMethod = "hystrixFallback")
	@RequestMapping(value = "/hello/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello(@PathVariable Integer id) throws Exception {
		//异常时自动调fallbackMethod
		if(id == 500 ) {
			throw new Exception("500 error!!"); 
		}
		return "hello word!";
	}
	
    public String hystrixFallback(Integer id) {
        return "500 error!";
    }
}