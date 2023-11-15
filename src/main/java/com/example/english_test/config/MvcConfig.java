package com.example.english_test.config;


import com.example.english_test.utils.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                       "/student_login",
                        "/englishTestFront/html/login.html",
                        "/englishTestFront/css/style1.css",
                        "/englishTestFront/css/style.css",
                        "/englishTestFront/img/**",
                        "/englishTestFront/getCodeImg",
                        "/student_login/**"

                ).order(1);
        // token刷新的拦截器
//        registry.addInterceptor(new RefreshInterceprot(stringRedisTemplate)).
//                addPathPatterns("/**").order(0);
    }
}
