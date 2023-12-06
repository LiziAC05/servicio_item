package com.example.servicio_item.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced
    public RestTemplate clienteRest (RestTemplateBuilder restTemplateBuilder){
        //permite que un restTemplate mapee el nombre con eureka
        return restTemplateBuilder.build();
    }
}
