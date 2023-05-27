package com.example.ebookbackend.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceUtil implements WebMvcConfigurer {
    static String path = System.getProperty("user.dir") + "/data/img/";

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**").addResourceLocations("file:" + path);
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }
}
