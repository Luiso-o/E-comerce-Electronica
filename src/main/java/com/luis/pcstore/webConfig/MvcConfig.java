package com.luis.pcstore.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "pcstore/public/images";
        String uploadPath = Paths.get(dirName).toFile().getAbsolutePath();

        registry.addResourceHandler("/images/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
