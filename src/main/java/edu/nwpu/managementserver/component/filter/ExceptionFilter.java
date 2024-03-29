package edu.nwpu.managementserver.component.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nwpu.managementserver.exception.ManagementException;
import edu.nwpu.managementserver.vo.CommonResult;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Slf4j
@Component
@Order(-2)
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

            log.error(e.getMessage());
            String content = mapper.writeValueAsString(CommonResult.failure(e));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(content);
        }
    }
}
