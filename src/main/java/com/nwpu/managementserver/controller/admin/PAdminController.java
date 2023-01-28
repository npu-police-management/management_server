package com.nwpu.managementserver.controller.admin;

import com.nwpu.managementserver.constant.RoleEnum;
import com.nwpu.managementserver.domain.Prison;
import com.nwpu.managementserver.domain.PrisonAdmin;
import com.nwpu.managementserver.dto.*;
import com.nwpu.managementserver.exception.ManagementException;
import com.nwpu.managementserver.service.AccountService;
import com.nwpu.managementserver.service.PrisonAdminService;
import com.nwpu.managementserver.service.PrisonService;
import com.nwpu.managementserver.util.PAdminNameGenerateUtil;
import com.nwpu.managementserver.util.PageTransformUtil;
import com.nwpu.managementserver.vo.CommonResult;
import com.nwpu.managementserver.vo.PageResult;
import com.nwpu.managementserver.vo.PrisonAdminVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
@RestController
@RequestMapping("/api/backstage-management-service/admin")
public class PAdminController {

    private PrisonService prisonService;

    private AccountService accountService;

    private PrisonAdminService prisonAdminService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPrisonService(PrisonService prisonService) {

        this.prisonService = prisonService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {

        this.accountService = accountService;
    }

    @Autowired
    public void setPrisonAdminService(PrisonAdminService prisonAdminService) {

        this.prisonAdminService = prisonAdminService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/prison/padmin")
    public CommonResult addPrisonAdmin(@Valid @RequestBody PrisonNameParam param) {

        try {
            Prison prison = prisonService.getPrisonByName(param.getPrisonName());
            String name = PAdminNameGenerateUtil.nextName(prison.getName());
            AccountUserDetails account = accountService.add(
                    name,
                    name,
                    RoleEnum.PrisonAdmin.getValue(),
                    passwordEncoder::encode);
            PrisonAdmin prisonAdmin = prisonAdminService.add(name, account.getId(), prison.getId());

            return CommonResult.success(new PAdminAccountDTO(
                    prisonAdmin.getId(),
                    prisonAdmin.getNickname(),
                    account.getAccountNumber()
            ));
        } catch (ManagementException e) {
            return CommonResult.failure(e);
        }
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/padmin")
    public CommonResult queryPAdmin(@Valid PagingQueryParam param) {

        PageResult<PrisonAdminVO> pageResult = PageTransformUtil.toViewPage(
                param,
                prisonAdminService::queryPrisonAdmin,
                prisonAdmin -> new PrisonAdminVO(
                        prisonAdmin.getId(),
                        prisonAdmin.getNickname(),
                        accountService.getById(prisonAdmin.getAccountId()).getAccountNumber(),
                        prisonService.getPrisonById(prisonAdmin.getPrisonId()).getName()
                )
        );
        return CommonResult.success(pageResult);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/prison/padmin")
    public CommonResult deleteById(@RequestBody IdListParam param) {

        prisonAdminService.deleteById(param.getIdList());
        return CommonResult.success();
    }
}
