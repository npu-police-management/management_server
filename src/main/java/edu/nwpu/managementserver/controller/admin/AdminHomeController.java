package edu.nwpu.managementserver.controller.admin;

import edu.nwpu.managementserver.mapper.PoliceTrainingMapper;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.vo.AdminDynamicVO;
import edu.nwpu.managementserver.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/3/5
 */
@RestController
@RequestMapping("/api/backstage-management-service/admin/mainPage")
public class AdminHomeController {

    @Autowired
    private PoliceTrainingService policeTrainingService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/dynamic")
    public CommonResult getDynamic() {

        List<AdminDynamicVO> adminDynamicVOList = policeTrainingService.getDynamic();
        return CommonResult.success(adminDynamicVOList);
    }

}
