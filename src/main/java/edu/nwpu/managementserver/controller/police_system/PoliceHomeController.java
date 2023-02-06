package edu.nwpu.managementserver.controller.police_system;

import edu.nwpu.managementserver.domain.Police;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.service.TrainingModelService;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PoliceDynamicVO;
import edu.nwpu.managementserver.vo.TrainingSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/2/6
 */
@RestController
@RequestMapping("/api/backstage-management-service/police/home")
public class PoliceHomeController {

    @Autowired
    private PoliceService policeService;

    @Autowired
    private PoliceTrainingService policeTrainingService;

    @Autowired
    private TrainingModelService trainingModelService;


    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("/training")
    public CommonResult trainingSituation(@AuthenticationPrincipal
                                              AccountUserDetails account) {

        Police police = policeService.getPoliceByAccountId(account.getId());

        TrainingSituationVO trainingSituationVO = policeTrainingService.getTrainingSituation(police.getId());
        return CommonResult.success(trainingSituationVO);
    }

    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("/dynamic")
    public CommonResult dynamic(@AuthenticationPrincipal
                                    AccountUserDetails account) {

        Police police = policeService.getPoliceByAccountId(account.getId());
        List<PoliceDynamicVO> dynamicVOList =
                policeTrainingService.getDynamic(police.getId())
                        .stream().map(policeTraining -> new PoliceDynamicVO(
                                trainingModelService.getById(policeTraining.getModelId()).getName(),
                                policeTraining.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                policeTraining.getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        )).toList();
        return CommonResult.success(dynamicVOList);
    }

    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("weekTraining")
    public CommonResult weekTraining(@AuthenticationPrincipal
                                         AccountUserDetails account) {

        Police police = policeService.getPoliceByAccountId(account.getId());
        List<Integer> list = policeTrainingService.weekTraining(police.getId());

        return CommonResult.success(list);
    }

    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("/isTraining")
    public CommonResult isTraining(@AuthenticationPrincipal
                                   AccountUserDetails account) {

        // account.getRole() == RoleEnum.Police
        Police police = policeService.getPoliceByAccountId(account.getId());
        boolean training = policeTrainingService.isTraining(police.getId());
        return CommonResult.success(training);
    }
}
