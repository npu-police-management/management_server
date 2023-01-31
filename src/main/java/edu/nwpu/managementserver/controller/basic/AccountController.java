package edu.nwpu.managementserver.controller.basic;

import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.PasswordChangeParam;
import edu.nwpu.managementserver.exception.ManagementException;
import edu.nwpu.managementserver.service.AccountService;
import edu.nwpu.managementserver.vo.CommonResult;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiayi Zhu
 * 2023/1/31
 */
@RestController
@RequestMapping("/api/backstage-management-service/account")
public class AccountController {

    @Value("${var.rsa.private-key}")
    private String privateKey;

    private AccountService accountService;

    private PasswordEncoder encoder;

    @Autowired
    public void setAccountService(AccountService accountService) {

        this.accountService = accountService;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {

        this.encoder = encoder;
    }

    @PutMapping("/password")
    public CommonResult changePassword(@Valid @RequestBody PasswordChangeParam param,
                                       @AuthenticationPrincipal AccountUserDetails account) {

        try {
            accountService.updatePassword(account, param.getDecryptParam(privateKey), encoder::encode);
            return CommonResult.success();
        } catch (ManagementException e) {
            return CommonResult.failure(e);
        }
    }
}
