package com.nacos.demo;

import java.util.Properties;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

public class SimpleNacosDemo {

	public static void main(String[] args) throws NacosException {

		//nacos地址
		String serverAddr = "127.0.0.1:8848";
		//Data Id
		String dataId = "nacos-simple-demo.yaml";
		
		//GROUP 
		String group = "DEFAULT_GROUP";
		
		Properties properties = new Properties();
		properties.put("serverAddr", serverAddr);
		
		ConfigService configService = NacosFactory.createConfigService(properties);
		
		String config = configService.getConfig(dataId, group, 5000);
		
		System.out.println(config);


	}

}
