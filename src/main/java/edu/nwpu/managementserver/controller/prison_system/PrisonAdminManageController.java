package edu.nwpu.managementserver.controller.prison_system;

import edu.nwpu.managementserver.constant.CodeEnum;
import edu.nwpu.managementserver.service.PrisonAdminService;
import edu.nwpu.managementserver.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author GengXuelong
 * @version 1.0
 * {@code Mail} 3349495429@qq.com
 * {@code time} 2023/3/4 1:01
 * {@code className} PrisonAdminManageController
 * {@code description}:
 */
@RestController
@RequestMapping("/api/backstage-management-service/prison")
public class PrisonAdminManageController {
    @Autowired
    PrisonAdminService prisonAdminService;
    @PutMapping("{prisonAdminId}")
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    public CommonResult update(@PathVariable("prisonAdminId") long prisonAdminId,@RequestParam String newNickname){
        boolean isSuccess = prisonAdminService.updatePrisonAdminName(prisonAdminId,newNickname);
        return isSuccess ? CommonResult.success():CommonResult.failure(CodeEnum.ServerError,"因服务器原因更新失败");
    }
}
