package com.jingluo.gateway.config;

import com.jingluo.gateway.filter.AuthFilter;
import com.jingluo.gateway.filter.WhitelistPathMatcher;
import com.jingluo.gateway.service.AuthService;
import jakarta.servlet.DispatcherType;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@NullMarked
public class FilterConfig {

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    private final AuthService authService;

    private final WhitelistPathMatcher pathMatcher;

    public FilterConfig(AuthService authService, WhitelistPathMatcher pathMatcher) {
        this.authService = authService;
        this.pathMatcher = pathMatcher;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuthFilter(resolver, authService, pathMatcher));
        registration.addUrlPatterns("/api/*");
        // 关键配置：只处理外部REQUEST，忽略FORWARD/INCLUDE等内部调度
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        return registration;
    }

}
