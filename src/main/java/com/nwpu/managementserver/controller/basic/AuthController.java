package com.nwpu.managementserver.controller.basic;

import com.nwpu.managementserver.component.JwtTokenProvider;
import com.nwpu.managementserver.dto.AccountUserDetails;
import com.nwpu.managementserver.dto.LoginParam;
import com.nwpu.managementserver.dto.TokenDTO;
import com.nwpu.managementserver.exception.ManagementException;
import com.nwpu.managementserver.service.AccessTokenService;
import com.nwpu.managementserver.service.AccountService;
import com.nwpu.managementserver.service.RefreshTokenService;
import com.nwpu.managementserver.vo.*;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.nwpu.managementserver.constant.CodeEnum.UserUnauthenticated;
import static com.nwpu.managementserver.constant.RequestAttributeConstant.AccessToken;
import static com.nwpu.managementserver.constant.RequestAttributeConstant.Token;

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

    private AccessTokenService accessTokenService;

    private RefreshTokenService refreshTokenService;

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

    @Autowired
    public void setAccessTokenService(AccessTokenService accessTokenService) {

        this.accessTokenService = accessTokenService;
    }

    @Autowired
    public void setRefreshTokenService(RefreshTokenService refreshTokenService) {

        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public CommonResult login(@Valid @RequestBody LoginParam param) {

        try {
            AccountUserDetails currentAccount =
                    (AccountUserDetails) accountService.loadUserByUsername(param.getAccountNumber());
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    currentAccount,
                                    param.getDecryptPassword(),
                                    currentAccount.getAuthorities()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            TokenDTO tokenDTO = jwtTokenProvider.generateToken(authentication);

            LoginVO loginVO = new LoginVO();
            loginVO.setAccessToken(tokenDTO.getAccessToken());
            loginVO.setRefreshToken(tokenDTO.getRefreshToken());
            loginVO.setRole(currentAccount.getRole().name());

            switch (currentAccount.getRole()) {
                case Police -> {
                    // TODO
                    PoliceLoginVO policeLoginVO = new PoliceLoginVO();
                    loginVO.setPerson(policeLoginVO);
                }
                case PrisonAdmin -> {
                    // TODO
                    PrisonAdminLoginVO prisonAdminLoginVO = new PrisonAdminLoginVO();
                    loginVO.setPerson(prisonAdminLoginVO);
                }
                case Admin -> {
                    // TODO
                    AdminLoginVO adminLoginVO = new AdminLoginVO();
                    loginVO.setPerson(adminLoginVO);
                }
            }
            return CommonResult.success(loginVO);
        } catch (AuthenticationException e) {
            return CommonResult.unauthorized(UserUnauthenticated, e.getMessage());
        } catch (ManagementException e) {
            return CommonResult.failure(e);
        }
    }

    @PostMapping("logout")
    public CommonResult logout(ServletRequest request,
                               @AuthenticationPrincipal AccountUserDetails account) {

        String accessToken = (String) request.getAttribute(AccessToken);
        accessTokenService.addToBlacklist(accessToken);

        String username = account.getUsername();
        refreshTokenService.deleteAllBySubject(username);

        return CommonResult.success();
    }

    @GetMapping("token-refresh")
    public CommonResult refreshToken(ServletRequest request) {

        return CommonResult.success(request.getAttribute(Token));
    }
}
