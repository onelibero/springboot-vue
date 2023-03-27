package com.comic.springbootproject.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
@AutoConfigureAfter(WebMvcConfigurer.class)
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private ResourceBean resourceBean;

    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 配置静态资源
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String os = System.getProperty("os.name");
//        if (os.toLowerCase().startsWith("win")) {
//            registry.addResourceHandler("/static/**")
//                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX +
//                            resourceBean.getWindowLocation());
//        }else {
//            registry.addResourceHandler("/static/**")
//                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX +
//                            resourceBean.getLinuxLocation());
//        }
//    }
}
