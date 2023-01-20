package com.nwpu.managementserver.component.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwpu.managementserver.exception.ManagementException;
import com.nwpu.managementserver.vo.CommonResult;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Slf4j
@Component
public class ExceptionFilter extends OncePerRequestFilter {

    private ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ManagementException e) {

            //LogUtils.logError(log, e);
            String content = mapper.writeValueAsString(CommonResult.failure(e.getMessage()));
            response.setContentType("application/json");
            response.getWriter().write(content);
        }
    }
}
