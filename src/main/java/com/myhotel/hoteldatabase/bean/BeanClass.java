package com.myhotel.hoteldatabase.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BeanClass {
    @Bean
    public RestTemplate getBean(){
        return new RestTemplate();
    }
}
