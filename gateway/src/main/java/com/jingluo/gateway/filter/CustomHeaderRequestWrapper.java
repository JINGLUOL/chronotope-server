package com.jingluo.gateway.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.*;

public class CustomHeaderRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String> customHeaders = new HashMap<>();

    public CustomHeaderRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    // 提供方法以便在 Filter 中添加 Header
    public void addHeader(String name, String value) {
        customHeaders.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        // 优先返回自定义 Header
        String headerValue = customHeaders.get(name);
        if (headerValue != null) {
            return headerValue;
        }
        // 否则返回原始请求的 Header
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        // 如果请求的是自定义 Header，且我们定义了它，则优先返回
        if (customHeaders.containsKey(name)) {
            return Collections.enumeration(List.of(customHeaders.get(name)));
        }
        return ((HttpServletRequest) getRequest()).getHeaders(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        // 合并自定义 Header 名称和原始 Header 名称
        Set<String> names = new HashSet<>(customHeaders.keySet());
        Enumeration<String> originalNames = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (originalNames.hasMoreElements()) {
            names.add(originalNames.nextElement());
        }
        return Collections.enumeration(names);
    }
}
