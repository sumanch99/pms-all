package com.cognizant.pms.gpd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class PensionDetailsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PensionDetailsApplication.class,args);
    }
}
