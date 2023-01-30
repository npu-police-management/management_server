package edu.nwpu.managementserver.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.constant.CodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String content = mapper.writeValueAsString(
                CommonResult.unauthorized(CodeEnum.UserUnauthenticated, authException.getMessage()));
        response.setContentType("application/json");
        response.getWriter().write(content);
    }
}
