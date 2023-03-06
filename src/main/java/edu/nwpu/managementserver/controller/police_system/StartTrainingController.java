package edu.nwpu.managementserver.controller.police_system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.nwpu.managementserver.domain.Police;
import edu.nwpu.managementserver.domain.PoliceTraining;
import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.PagingQueryParam;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.service.PrisonModelService;
import edu.nwpu.managementserver.service.TrainingModelService;
import edu.nwpu.managementserver.util.DataTrainingUtils;
import edu.nwpu.managementserver.util.PageTransformUtil;
import edu.nwpu.managementserver.util.SnowflakeIdUtil;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.TotalAssessForPoliceVO;
import edu.nwpu.managementserver.vo.TrainingModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GengXuelong
 * @version 1.0
 * {@code Mail} 3349495429@qq.com
 * {@code time} 2023/3/4 1:42
 * {@code className} StartTrainingController
 * {@code description}:
 */
@RestController
@RequestMapping("/api/backstage-management-service")
public class StartTrainingController {
    @Autowired
    private PoliceService policeService;
    @Autowired
    private TrainingModelService trainingModelService;
    @Autowired
    private PoliceTrainingService policeTrainingService;
    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * {code description}:
     *     通过训练模型id进行警员训练动作
     */
    @PreAuthorize("hasAuthority('Police')")
    @PostMapping("/training/{modelId}")
    public CommonResult handleTraining(@AuthenticationPrincipal AccountUserDetails account, @PathVariable long modelId){
        long account_id = account.getId();
        Police police = policeService.getPoliceByAccountId(account_id);
        int status = DataTrainingUtils.getStatus(police.getId(),modelId);
        String result = DataTrainingUtils.getResult(police.getId(),modelId);
        LocalDateTime startTime =  LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(1);
        PoliceTraining policeTraining = new PoliceTraining(SnowflakeIdUtil.nextId(),police.getId(),modelId,startTime,endTime,status,result);
        policeTrainingService.addOne(policeTraining);
        return CommonResult.success();
    }

    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("/model")
    public CommonResult getModelList(@AuthenticationPrincipal AccountUserDetails account, PagingQueryParam param){
        long account_id = account.getId();
        Police police = policeService.getPoliceByAccountId(account_id);
//        List<TrainingModel> trainingModelForPrisonAdmin = trainingModelService.getTrainingModelForPrisonAdmin("");
//        List<TrainingModel> collect = trainingModelForPrisonAdmin.stream().filter(trainingModel -> prisonModelService.exist(trainingModel.getId(), police.getPrisonId())).toList();
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<TrainingModel> trainingModels = trainingModelService.getTrainingModelForPolice(police.getPrisonId(),param.getQuery());
        PageInfo<TrainingModel> tPageInfo = new PageInfo<>(trainingModels);
        return CommonResult.success(new PageResult<>(
                (int)tPageInfo.getTotal(),
                trainingModels.stream().map(trainingModel -> new TrainingModelVO(trainingModel.getId().toString(),trainingModel.getName(),trainingModel.getDescription(),true,trainingModel.getPriority())).toList()
        ));
    }
}
