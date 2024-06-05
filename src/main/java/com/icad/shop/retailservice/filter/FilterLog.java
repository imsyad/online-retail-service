package com.icad.shop.retailservice.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.utils.DateUtils;
import org.springframework.core.annotation.Order;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Component
@Order(1)
@Slf4j
public class FilterLog implements Filter {

    public FilterLog() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.doFilterWrapped((HttpServletRequestWrapper) servletRequest, (HttpServletResponseWrapper) servletResponse, filterChain);
    }

    protected void doFilterWrapped(HttpServletRequestWrapper httpServletRequestWrapper, HttpServletResponseWrapper httpServletResponseWrapper, FilterChain filterChain) throws ServletException, IOException {
        Instant startTime = Instant.MIN;
        Instant endTime;

        try {
            startTime = Instant.now();
            filterChain.doFilter(httpServletRequestWrapper, httpServletResponseWrapper);
        } finally {
            endTime = Instant.now();
            this.afterRequest(startTime, endTime, httpServletRequestWrapper, httpServletResponseWrapper);
        }
    }

    protected void afterRequest(Instant startTime, Instant endTime, HttpServletRequestWrapper httpServletRequestWrapper, HttpServletResponseWrapper httpServletResponseWrapper) throws IOException {
        this.log(startTime, endTime, httpServletRequestWrapper, httpServletResponseWrapper, httpServletRequestWrapper.getInputStream().readAllBytes());
    }

    protected void log(Instant startTime, Instant endTime, HttpServletRequestWrapper httpServletRequestWrapper, HttpServletResponseWrapper httpServletResponseWrapper, byte[] body) {
        Duration processTime = Duration.between(startTime, endTime);
        String requestTime = DateUtils.formatDate(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        String responseTime = DateUtils.formatDate(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("========================================================================");
        sb.append("\n");
        sb.append(httpServletRequestWrapper.getRequestURI()).append("\n");
        sb.append(requestTime).append("\n");
        if (body.length > 0) {
            sb.append(new String(body, StandardCharsets.UTF_8)).append("\n");
        }
        sb.append("Process Time: ").append(processTime.toMillis()).append("ms\n");
        sb.append("========================================================================");
        log.info(sb.toString());
    }
}
