package com.jingluo.gateway.filter;

import com.jingluo.gateway.constant.AuthResult;
import com.jingluo.gateway.service.AuthService;
import com.jingluo.gateway.exception.UnauthorizedException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NullMarked;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@NullMarked
public class AuthFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver resolver;

    private final AuthService authService;

    private final WhitelistPathMatcher pathMatcher;

    public AuthFilter(
            HandlerExceptionResolver resolver,
            AuthService authService,
            WhitelistPathMatcher pathMatcher
    ) {
        this.resolver = resolver;
        this.authService = authService;
        this.pathMatcher = pathMatcher;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String uri = request.getRequestURI();

        // 白名单直接放行
        if (pathMatcher.matches(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 获取令牌
        String token = request.getHeader("Authorization");

        // 鉴权
        AuthResult result;
        try {
            result = authService.verify(token);
            if (!result.authenticated()) {
                resolver.resolveException(request, response, null, new UnauthorizedException(result));
                return;
            }
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
            return;
        }

        // 标记用户
        CustomHeaderRequestWrapper wrappedRequest = new CustomHeaderRequestWrapper(request);
        wrappedRequest.addHeader("X-User-Agent", String.valueOf(result.userId()));

        filterChain.doFilter(wrappedRequest, response);
    }

}
