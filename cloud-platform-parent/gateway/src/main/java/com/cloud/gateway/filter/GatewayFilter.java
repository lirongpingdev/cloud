package com.cloud.gateway.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@Component
@Slf4j
public class GatewayFilter implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		List<String> list = exchange.getRequest().getHeaders().get("Authorization");
		MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
		log.info("----------list-------------"+new Gson().toJson(list));
		log.info("----------queryParams-------------"+new Gson().toJson(queryParams));
		log.info("----------exchange.getRequest().getHeaders()-------------"+new Gson().toJson(exchange.getRequest().getHeaders()));
		/*if(exchange.getRequest().getHeaders().get("Authorization") == null) {
			exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
			log.error("Authorization is null.");
			return exchange.getResponse().setComplete();
		}*/
		return chain.filter(exchange);
	}

}
