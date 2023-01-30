package com.nwpu.managementserver.controller.police_system;

import com.github.pagehelper.util.StringUtil;
import com.nwpu.managementserver.domain.Police;
import com.nwpu.managementserver.domain.Prison;
import com.nwpu.managementserver.domain.PrisonAdmin;
import com.nwpu.managementserver.dto.AccountUserDetails;
import com.nwpu.managementserver.dto.PoliceUpdateParam;
import com.nwpu.managementserver.service.PoliceService;
import com.nwpu.managementserver.service.PrisonAdminService;
import com.nwpu.managementserver.service.PrisonService;
import com.nwpu.managementserver.vo.CommonResult;
import com.nwpu.managementserver.vo.PoliceToSelfVO;
import com.nwpu.managementserver.vo.PoliceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 18:59
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.controller.police_system.PoliceController
 * @description:
 */
@RestController
@RequestMapping("/api/backstage-management-service/police")
public class PoliceController {

    @Autowired
    PoliceService policeService;
    @Autowired
    PrisonService prisonService;
    @Autowired
    PrisonAdminService prisonAdminService;

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     警员更新自己的信息
     */
    @PreAuthorize("hasAuthority('Police')")
    @PutMapping("/profile/{id}")
    public CommonResult update(@RequestBody PoliceUpdateParam param, @PathVariable("id")long id){
        Police police = policeService.getPoliceById(id);
        police.setName(param.getName());
        police.setImageUrl(param.getImageUrl());
        if(!StringUtil.isEmpty(param.getPrisonName())){
            Prison prisonByName = prisonService.getPrisonByName(param.getPrisonName());
            police.setPrisonId(prisonByName.getId());
        }
        policeService.update(police);
        return CommonResult.success();
    }


   /**
    * @author GengXuelong
    * <p> 函数功能描述如下:
    * @description:
    *     警员系统得到该警员的详细信息
    */
//    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("/profile")
    public CommonResult getMyDetail(@AuthenticationPrincipal AccountUserDetails account){
        Long account_id = account.getId();
        String accountNumber = account.getAccountNumber();
        PrisonAdmin byAccountId = prisonAdminService.getByAccountId(account_id);
        Prison prisonById = prisonService.getPrisonById(byAccountId.getPrisonId());
        Police police = policeService.getPoliceByAccountId(account_id);
        PoliceToSelfVO policeVO = new PoliceToSelfVO(police.getId()+"",police.getName(),accountNumber,police.getImageUrl(),prisonById.getName());
        return CommonResult.success(policeVO);
    }
}
