package com.jrock.boot.springboot.config;

import com.jrock.boot.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
// LoginUserArgumentResolver를 스프링에서 인식될 수 있도록 WebMvcConfigurer 추가
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    // HandlerMethodArgumentResolver 는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야 한다.
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
