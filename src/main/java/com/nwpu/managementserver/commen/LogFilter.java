package com.nwpu.managementserver.commen;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@Order(-1)
public class LogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("traceId", String.valueOf(request.getHeader("X-B3-Traceid")));
        MDC.put("userId", "0");
        if(!request.getRequestURI().startsWith("/actuator")){
            log.info("请求方法 {} URI {} Query {}", request.getMethod(),request.getRequestURI(),request.getQueryString());
        }
        filterChain.doFilter(request, response);
    }
}