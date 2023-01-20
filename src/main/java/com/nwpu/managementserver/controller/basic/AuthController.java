package com.nwpu.managementserver.controller.basic;

import com.nwpu.managementserver.component.JwtTokenProvider;
import com.nwpu.managementserver.dto.LoginParam;
import com.nwpu.managementserver.dto.TokenDTO;
import com.nwpu.managementserver.service.AccountService;
import com.nwpu.managementserver.vo.CommonResult;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@RestController
@RequestMapping("/api/backstage-management-service")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private AccountService accountService;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {

        this.accountService = accountService;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {

        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public CommonResult login(@Valid @RequestBody LoginParam param) {

        try {
            UserDetails currentAccount = accountService.loadUserByUsername(param.getAccountNumber());
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    currentAccount,
                                    param.getDecryptPassword(),
                                    currentAccount.getAuthorities()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TokenDTO tokenDTO = jwtTokenProvider.generateToken(authentication);
            return CommonResult.success(tokenDTO);
        } catch (AuthenticationException e) {
            return CommonResult.unauthorized(e.getMessage());
        }
    }
}
