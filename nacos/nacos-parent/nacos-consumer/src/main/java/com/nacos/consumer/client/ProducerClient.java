package com.nacos.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "nacos-producer")
public interface ProducerClient {

    @GetMapping("/service")
    public String service();
}
