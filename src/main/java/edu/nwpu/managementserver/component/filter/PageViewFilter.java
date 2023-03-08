package edu.nwpu.managementserver.component.filter;

import edu.nwpu.managementserver.service.PageViewService;
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
 * 2023/3/5
 */
@Slf4j
@Component
public class PageViewFilter extends OncePerRequestFilter {

    @Autowired
    private PageViewService pageViewService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

//        log.info("");
        if ("GET".equals(request.getMethod())) {
            String uri = request.getRequestURI();
            String id = request.getRequestedSessionId();
            String info = id + " " + uri;
            pageViewService.saveOneView(info);
        }

        filterChain.doFilter(request, response);
    }
}
