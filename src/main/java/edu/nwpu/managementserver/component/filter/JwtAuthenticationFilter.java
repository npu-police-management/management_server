package edu.nwpu.managementserver.component.filter;

import edu.nwpu.managementserver.component.JwtTokenProvider;
import edu.nwpu.managementserver.dto.TokenDTO;
import edu.nwpu.managementserver.exception.JwtAuthException;
import edu.nwpu.managementserver.service.AccessTokenService;
import edu.nwpu.managementserver.constant.CodeEnum;
import edu.nwpu.managementserver.constant.RequestAttributeConstant;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider tokenProvider;

    private UserDetailsService userDetailsService;

    private AccessTokenService accessTokenService;

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {

        this.tokenProvider = tokenProvider;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setAccessTokenService(AccessTokenService accessTokenService) {

        this.accessTokenService = accessTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // get JWT (token) from request
        String token = getJwtFromRequest(request);

        // check is logout
        if (accessTokenService.existInBlacklist(token)) {
            throw new JwtAuthException(CodeEnum.UserUnauthenticated, "用户未登录");
        }

        // validate token
        if (StringUtils.hasText(token)) {
            tokenProvider.validateToken(token);

            // is-refresh-token
            if (StringUtils.hasText(tokenProvider.fromToken(token, Claims::getId))) {
                try {
                    tokenProvider.checkRefreshToken(token);
                    TokenDTO tokenDTO = tokenProvider.generateToken(token);
                    request.setAttribute(RequestAttributeConstant.Token, tokenDTO);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            else {
                // get username from token
                String username = tokenProvider.fromToken(token, Claims::getSubject);
                // lead user associated with token
                UserDetails account = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        account, null, account.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                // set spring security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                request.setAttribute(RequestAttributeConstant.AccessToken, token);
            }


        }
        filterChain.doFilter(request, response);
    }

    // Bearer <token>
    private String getJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return "";
    }
}
