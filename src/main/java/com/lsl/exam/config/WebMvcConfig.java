package com.lsl.exam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置WebMvcConfig
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置跨域
     * allowedOriginPatterns 和 allowedOrigins都是配置跨域请求来源的,
     * 前者支持通配符,后者不支持通配符,只能字符串
     * 取其一种即可
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")//配置当前项目哪些路径支持跨域。这是所有路径都支持跨域
//                .allowedOriginPatterns("*")//配置跨域请求来源。通配符配置方式。这是所有请求来源都可以
                .allowedOrigins("http://localhost:8010","http://www.exam.com:8010")//配置跨域请求来源,字符串配置，配置这有这2个请求来源的跨域请求访问
                .allowedMethods("GET","POST")//设置允许所有请求方式
                .allowedHeaders("*")//设置允许的header
                .allowCredentials(true)//设置是否发送cookie信息
                .maxAge(3600);
    }
}
