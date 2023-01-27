package com.nwpu.managementserver.controller.admin;

import com.nwpu.managementserver.dto.PrisonNameParam;
import com.nwpu.managementserver.service.PrisonService;
import com.nwpu.managementserver.util.SnowflakeIdUtil;
import com.nwpu.managementserver.vo.CommonResult;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
@RestController
@RequestMapping("/api/backstage-management-service/admin/prison")
public class PrisonController {

    private PrisonService prisonService;

    @Autowired
    public void setPrisonService(PrisonService prisonService) {

        this.prisonService = prisonService;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("")
    public CommonResult addPrison(@Valid @RequestBody PrisonNameParam param) {

        long id = SnowflakeIdUtil.nextId();
        prisonService.addPrison(id, param.getPrisonName());
        return CommonResult.success();
    }
}
