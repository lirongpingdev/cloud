package com.nacos.dataid.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataIdController {
    @Value("${service.name}")
    private String name;
  
    @GetMapping(value = "/name")
    public String getName() {
      return name;
    }
}
