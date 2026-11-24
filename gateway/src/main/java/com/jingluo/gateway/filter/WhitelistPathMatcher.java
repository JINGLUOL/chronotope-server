package com.jingluo.gateway.filter;

import com.jingluo.gateway.constant.WhitelistProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

@Component
public class WhitelistPathMatcher {

    private final WhitelistProperties properties;
    private final PathPatternParser parser = new PathPatternParser();

    /**
     * 启动时一次性编译并缓存，避免每次请求重复 parse
     */
    private List<PathPattern> compiledPatterns = List.of();

    public WhitelistPathMatcher(WhitelistProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        this.compiledPatterns = properties.getPaths().stream()
                .map(parser::parse)
                .toList();
    }

    /**
     * 判断路径是否命中白名单
     *
     * @param rawPath 原始请求路径（不含 query，未经 StripPrefix 处理）
     */
    public boolean matches(String rawPath) {
        if (rawPath == null || rawPath.isBlank()) return false;
        PathContainer container = PathContainer.parsePath(rawPath);
        return compiledPatterns.stream().anyMatch(p -> p.matches(container));
    }

}
