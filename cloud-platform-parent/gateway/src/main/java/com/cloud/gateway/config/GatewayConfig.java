package com.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	@Bean
	public RouteLocator routeLocatorEnt(RouteLocatorBuilder routeLocatorBuilder) {
		//路由地址：https://tuijian.hao123.com/ent
		RouteLocatorBuilder.Builder routes= routeLocatorBuilder.routes();
		routes.route("route-hao123-ent",r -> r.path("/ent").uri("https://tuijian.hao123.com/ent")).build();
		return routes.build();
	}
	
	@Bean
	public RouteLocator routeLocatorSports(RouteLocatorBuilder routeLocatorBuilder) {
		//路由地址：https://tuijian.hao123.com/sports
		RouteLocatorBuilder.Builder routes= routeLocatorBuilder.routes();
		routes.route("route-hao123-sports",r -> r.path("/sports").uri("https://tuijian.hao123.com/sports")).build();
		return routes.build();
	}
	
}