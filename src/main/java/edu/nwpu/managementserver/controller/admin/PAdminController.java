package edu.nwpu.managementserver.controller.admin;

import edu.nwpu.managementserver.constant.RoleEnum;
import edu.nwpu.managementserver.domain.Prison;
import edu.nwpu.managementserver.domain.PrisonAdmin;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.IdListParam;
import edu.nwpu.managementserver.dto.PagingQueryParam;
import edu.nwpu.managementserver.dto.PrisonNameParam;
import edu.nwpu.managementserver.exception.ManagementException;
import edu.nwpu.managementserver.service.AccountService;
import edu.nwpu.managementserver.service.PrisonAdminService;
import edu.nwpu.managementserver.service.PrisonService;
import edu.nwpu.managementserver.util.PAdminNameGenerateUtil;
import edu.nwpu.managementserver.util.PageTransformUtil;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.PrisonAdminVO;
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

    @PreAuthorize("hasAuthority('Admin')")
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

            return CommonResult.success(new PrisonAdminVO(
                    prisonAdmin.getId().toString(),
                    prisonAdmin.getNickname(),
                    account.getAccountNumber(),
                    param.getPrisonName()
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
                        prisonAdmin.getId().toString(),
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
