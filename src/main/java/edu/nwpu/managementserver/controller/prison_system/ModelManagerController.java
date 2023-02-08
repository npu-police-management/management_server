package edu.nwpu.managementserver.controller.prison_system;

import edu.nwpu.managementserver.domain.PrisonModel;
import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.PagingQueryParam;
import edu.nwpu.managementserver.service.PrisonAdminService;
import edu.nwpu.managementserver.service.PrisonModelService;
import edu.nwpu.managementserver.service.TrainingModelService;
import edu.nwpu.managementserver.util.PageTransformUtil;
import edu.nwpu.managementserver.util.SnowflakeIdUtil;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.TrainingModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 15:27
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.controller.prison_system.ModelManagerController
 * @description:
 */
@RestController
@RequestMapping("/api/backstage-management-service/prison")
public class ModelManagerController {
    @Autowired
    private TrainingModelService trainingModelService;
    @Autowired
    private PrisonAdminService prisonAdminService;
    @Autowired
    private PrisonModelService prisonModelService;

    @GetMapping("model")
    public CommonResult modelVOList(PagingQueryParam pagingQueryParam){
        Function<TrainingModel,TrainingModelVO>  mapper = (trainingModel)->new TrainingModelVO(trainingModel.getId()+"",trainingModel.getName(),trainingModel.getDescription(),false,trainingModel.getPriority());
	PageResult<TrainingModelVO> pageResult = PageTransformUtil.toViewPage(
		pagingQueryParam,
		trainingModelService::getTrainingModelForPrisonAdmin,
		mapper
	);
	return CommonResult.success(pageResult);
    }


    @PutMapping("/model/{id}")
    public CommonResult modifyModel(@PathVariable("id")long modelId,@RequestParam("enable")boolean enable,
				    @AuthenticationPrincipal AccountUserDetails account){
	long account_id = account.getId();
	long prison_id = prisonAdminService.getPrisonIdByAccountId(account_id);
        if(enable){
            if(!prisonModelService.exist(modelId,prison_id)){
		prisonModelService.addOne(new PrisonModel(SnowflakeIdUtil.nextId(),prison_id,modelId));
	    }
	}else{
	    if(prisonModelService.exist(modelId,prison_id)){
		prisonModelService.deleteOne(prison_id,modelId);
	    }
	}
        return CommonResult.success();
    }

//    @GetMapping("trainDynamic")
//    public CommonResult trainDynamic(){
//        List<String> modelNameList = trainingModelService.getTrainingModelNameForPrisonAdmin();
//        return CommonResult.success(modelNameList);
//    }

}
