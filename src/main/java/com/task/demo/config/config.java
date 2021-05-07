package com.task.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration

public class config implements WebMvcConfigurer {
    String myExternalFilePath = "file:///D:/gs-authenticating-ldap-master/gs-authenticating-ldap-master/inventoryMS/uploads/"; // end your path with a /

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations(myExternalFilePath).addResourceLocations("classpath:/static/");
    }

}
