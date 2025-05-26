package com.capstone.scheduledevelop.config;

import com.capstone.scheduledevelop.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 로그인 필터 등록
    @Bean
    public FilterRegistrationBean loginFilter() {

        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();

        // 필터로 사용할 LoginFilter 인스턴스 설정
        filterFilterRegistrationBean.setFilter(new LoginFilter());
        // 필터의 순서 설정
        filterFilterRegistrationBean.setOrder(1);
        // 필터가 적용될 URL 패턴 설정
        filterFilterRegistrationBean.addUrlPatterns("/*");

        return filterFilterRegistrationBean;

    }
}
