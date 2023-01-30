package edu.nwpu.managementserver.controller.basic;

import edu.nwpu.managementserver.component.JwtTokenProvider;
import edu.nwpu.managementserver.domain.Admin;
import edu.nwpu.managementserver.domain.Prison;
import edu.nwpu.managementserver.domain.PrisonAdmin;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.LoginParam;
import edu.nwpu.managementserver.dto.TokenDTO;
import edu.nwpu.managementserver.exception.ManagementException;
import edu.nwpu.managementserver.service.*;
import edu.nwpu.managementserver.vo.*;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static edu.nwpu.managementserver.constant.CodeEnum.UserUnauthenticated;
import static edu.nwpu.managementserver.constant.RequestAttributeConstant.AccessToken;
import static edu.nwpu.managementserver.constant.RequestAttributeConstant.Token;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@RestController
@RequestMapping("/api/backstage-management-service")
public class AuthController {

    @Value("${var.rsa.private-key}")
    private String privateKey;

    private AuthenticationManager authenticationManager;

    private AccountService accountService;

    private JwtTokenProvider jwtTokenProvider;

    private AccessTokenService accessTokenService;

    private RefreshTokenService refreshTokenService;

    private PrisonAdminService prisonAdminService;

    private PrisonService prisonService;

    private AdminService adminService;

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

    @Autowired
    public void setPrisonAdminService(PrisonAdminService prisonAdminService) {

        this.prisonAdminService = prisonAdminService;
    }

    @Autowired
    public void setPrisonService(PrisonService prisonService) {

        this.prisonService = prisonService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {

        this.adminService = adminService;
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
                                    param.getDecryptPassword(privateKey),
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
                    PrisonAdmin prisonAdmin = prisonAdminService.getByAccountId(currentAccount.getId());
                    Prison prison = prisonService.getPrisonById(prisonAdmin.getPrisonId());
                    loginVO.setPerson(new PrisonAdminLoginVO(
                            prisonAdmin.getId().toString(), prisonAdmin.getNickname(), prison.getName()
                    ));
                }
                case Admin -> {
                    Admin admin = adminService.getByAccountId(currentAccount.getId());
                    loginVO.setPerson(new AdminLoginVO(
                            admin.getId().toString(), admin.getNickname()
                    ));
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
