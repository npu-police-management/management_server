package edu.nwpu.managementserver.config;

import edu.nwpu.managementserver.commen.LogFilter;
import edu.nwpu.managementserver.component.JwtAuthenticationEntryPoint;
import edu.nwpu.managementserver.component.filter.ExceptionFilter;
import edu.nwpu.managementserver.component.filter.JwtAuthenticationFilter;
import edu.nwpu.managementserver.component.filter.PageViewFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private ExceptionFilter exceptionFilter;

    private UserDetailsService userDetailsService;

    private LogFilter logFilter;

    @Autowired
    private PageViewFilter pageViewFilter;

    @Autowired
    public void setAuthenticationEntryPoint(JwtAuthenticationEntryPoint authenticationEntryPoint) {

        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    public void setJwtAuthenticationFilter(JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Autowired
    public void setExceptionFilter(ExceptionFilter exceptionFilter) {

        this.exceptionFilter = exceptionFilter;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setLogFilter(LogFilter logFilter) {

        this.logFilter = logFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/**", "/actuator/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(pageViewFilter, JwtAuthenticationFilter.class)
                .addFilterBefore(logFilter, JwtAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, LogFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {

        return authentication -> {
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            daoAuthenticationProvider.setUserDetailsService(userDetailsService);

            return new ProviderManager(daoAuthenticationProvider).authenticate(authentication);
        };
    }
}
