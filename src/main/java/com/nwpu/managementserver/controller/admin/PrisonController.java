package com.nwpu.managementserver.controller.admin;

import com.nwpu.managementserver.dto.IdListParam;
import com.nwpu.managementserver.dto.PagingQueryParam;
import com.nwpu.managementserver.dto.PrisonNameParam;
import com.nwpu.managementserver.exception.ManagementException;
import com.nwpu.managementserver.service.PrisonService;
import com.nwpu.managementserver.util.PageTransformUtil;
import com.nwpu.managementserver.vo.CommonResult;
import com.nwpu.managementserver.vo.PageResult;
import com.nwpu.managementserver.vo.PrisonVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

        try {
            prisonService.addPrison(param.getPrisonName());
            return CommonResult.success();
        } catch (ManagementException e) {
            return CommonResult.failure(e);
        }
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("")
    public CommonResult queryPrison(@Valid PagingQueryParam param) {

        PageResult<PrisonVO> pageResult = PageTransformUtil.toViewPage(
                param,
                prisonService::queryPrison,
                prison -> new PrisonVO(prison.getName())
        );
        return CommonResult.success(pageResult);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("")
    public CommonResult deletePrisonById(@RequestBody IdListParam param) {

        prisonService.deleteById(param.getIdList());
        return CommonResult.success();
    }
}
