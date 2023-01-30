package edu.nwpu.managementserver.controller.admin;

import edu.nwpu.managementserver.domain.Prison;
import edu.nwpu.managementserver.dto.IdListParam;
import edu.nwpu.managementserver.dto.PagingQueryParam;
import edu.nwpu.managementserver.dto.PrisonNameParam;
import edu.nwpu.managementserver.exception.ManagementException;
import edu.nwpu.managementserver.service.PrisonService;
import edu.nwpu.managementserver.util.PageTransformUtil;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.PrisonVO;
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
            Prison p = prisonService.addPrison(param.getPrisonName());
            return CommonResult.success(new PrisonVO(p.getId().toString(), p.getName()));
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
                prison -> new PrisonVO(prison.getId().toString(), prison.getName())
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
