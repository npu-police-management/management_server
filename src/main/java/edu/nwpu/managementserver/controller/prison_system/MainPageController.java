package edu.nwpu.managementserver.controller.prison_system;

import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.mapper.AccessRecordMapper;
import edu.nwpu.managementserver.mapper.PrisonModelMapper;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.service.PrisonAdminService;
import edu.nwpu.managementserver.service.PrisonService;
import edu.nwpu.managementserver.vo.PrisonAdminMainPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private PrisonModelMapper prisonModelMapper;
    @Autowired
    private AccessRecordMapper accessRecordMapper;
    @Autowired
    private PoliceTrainingService policeTrainingService;
    @Autowired
    private PrisonAdminService prisonAdminService;

    @GetMapping("/mainPage")
    public PrisonAdminMainPageVO mainPage( @AuthenticationPrincipal AccountUserDetails account){
        long account_id = account.getId();
        long prison_id = prisonAdminService.getPrisonIdByAccountId(account_id);
        PrisonAdminMainPageVO prisonAdminMainPageVO = new PrisonAdminMainPageVO();
        prisonAdminMainPageVO.setFinishTrainTimeWeekly(policeTrainingService.getNumberWeekFinish(prison_id)+"");
        return null;
    }



}
