package edu.nwpu.managementserver.controller.police_system;

import com.github.pagehelper.util.StringUtil;
import edu.nwpu.managementserver.domain.Police;
import edu.nwpu.managementserver.domain.Prison;
import edu.nwpu.managementserver.domain.PrisonAdmin;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.PoliceUpdateParam;
import edu.nwpu.managementserver.dto.PoliceUpdateSelfParam;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.service.PrisonAdminService;
import edu.nwpu.managementserver.service.PrisonService;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PoliceToSelfVO;
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
    public CommonResult update(@RequestBody PoliceUpdateSelfParam param, @PathVariable("id")long id){
        Police police = policeService.getPoliceById(id);
        police.setName(param.getName());
        police.setImageUrl(param.getImageUrl());
        policeService.update(police);
        return CommonResult.success();
    }


   /**
    * @author GengXuelong
    * <p> 函数功能描述如下:
    * @description:
    *     警员系统得到该警员的详细信息
    */
    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("/profile")
    public CommonResult getMyDetail(@AuthenticationPrincipal AccountUserDetails account){
        Long account_id = account.getId();
        System.out.println(account_id);
        String accountNumber = account.getAccountNumber();
        Police police = policeService.getPoliceByAccountId(account_id);
        Prison prisonById = prisonService.getPrisonById(police.getPrisonId());
        PoliceToSelfVO policeVO = new PoliceToSelfVO(police.getId()+"",police.getName(),accountNumber,police.getImageUrl(),prisonById.getName());
        return CommonResult.success(policeVO);
    }
}
