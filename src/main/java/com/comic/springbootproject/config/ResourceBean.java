package com.comic.springbootproject.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource(value = "classpath:application.properties")
public class ResourceBean {
    @Value("${resource.path}")
    private String path;
    @Value("${resource.path.pattern}")
    private String pathPattern;
    @Value("${resource.local.path.windows}")
    private String windowLocation;
    @Value("${resource.local.path.linux}")
    private String linuxLocation;
}
