package edu.nwpu.managementserver.controller.police_system;

import edu.nwpu.managementserver.domain.Police;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.TotalAssessForPoliceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author GengXuelong
 * @version 1.0
 * {@code Mail} 3349495429@qq.com
 * {@code time} 2023/3/4 1:42
 * {@code className} StartTrainingController
 * {@code description}:
 */
@RestController
@RequestMapping("/api/backstage-management-service/")
public class StartTrainingController {
    @Autowired
    private PoliceService policeService;
    @PreAuthorize("hasAuthority('Police')")
    @PostMapping("/training/{modelId}")
    public CommonResult handleTraining(@AuthenticationPrincipal AccountUserDetails account, @PathVariable String modelId){
        long account_id = account.getId();
        Police police = policeService.getPoliceByAccountId(account_id);
        return null;
    }

    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("/model")
    public CommonResult getModelList(){
        return null;
    }

}
