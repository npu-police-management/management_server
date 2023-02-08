package edu.nwpu.managementserver.controller.prison_system;

import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.mapper.AccessRecordMapper;
import edu.nwpu.managementserver.mapper.PrisonModelMapper;
import edu.nwpu.managementserver.service.*;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PrisonAdminMainPageDynamicVO;
import edu.nwpu.managementserver.vo.PrisonAdminMainPageStatsVO;
import edu.nwpu.managementserver.vo.PrisonAdminMainPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 20:05
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.controller.prison_system.MainPageController
 * @description:
 */
@RestController
@RequestMapping("/api/backstage-management-service/prison")
public class MainPageController {
    @Autowired
    private PrisonModelService prisonModelService;
    @Autowired
    private AccessRecordService accessRecordService;
    @Autowired
    private PoliceTrainingService policeTrainingService;
    @Autowired
    private PrisonAdminService prisonAdminService;

    @GetMapping("/mainPage")
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    public CommonResult mainPage(@AuthenticationPrincipal AccountUserDetails account){
        long account_id = account.getId();
        long prison_id = prisonAdminService.getPrisonIdByAccountId(account_id);
        PrisonAdminMainPageVO prisonAdminMainPageVO = new PrisonAdminMainPageVO();
        prisonAdminMainPageVO.setFinishTrainTimeWeekly(policeTrainingService.getNumberWeekFinish(prison_id)+"");
        prisonAdminMainPageVO.setFinishTrainTimeDaily(policeTrainingService.getNumberTodayFinish(prison_id)+"");
        prisonAdminMainPageVO.setWorkingModeNumber(prisonModelService.getTheOpeningModeSizeForPrison(prison_id)+"");
        prisonAdminMainPageVO.setLoginTimeDaily(accessRecordService.getNumberTodayAccess(prison_id)+"");
        return CommonResult.success(prisonAdminMainPageVO);
    }

    @GetMapping("/mainPage/dynamic")
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    public CommonResult mainPageDynamic( @AuthenticationPrincipal AccountUserDetails account){
        long account_id = account.getId();
        long prison_id = prisonAdminService.getPrisonIdByAccountId(account_id);
        return CommonResult.success(policeTrainingService.getThreeDate(prison_id));
    }

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     本周本监所警员对各场景的完成人次，仅显示不为零的场景模型，且忽略超级管理员是否关闭了这个模型
     */
    @GetMapping("/mainPage/stats")
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    public CommonResult mainPageStats(@AuthenticationPrincipal AccountUserDetails account){
        long account_id = account.getId();
        long prison_id = prisonAdminService.getPrisonIdByAccountId(account_id);
        return CommonResult.success(policeTrainingService.getWeeklyStatus(prison_id));
    }




}
