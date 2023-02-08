package edu.nwpu.managementserver.controller.prison_system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.PagingQueryParamForTrainingDynamic;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.service.PrisonAdminService;
import edu.nwpu.managementserver.service.TrainingModelService;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.TrainingDynamicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 16:15
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.controller.prison_system.PoliceTrainingDynamicController
 * @description:
 */
@RestController
@RequestMapping("/api/backstage-management-service/prison")
public class PrisonTrainingDynamicController {
    @Autowired
    private TrainingModelService trainingModelService;
    @Autowired
    private PrisonAdminService prisonAdminService;
    @Autowired
    private PoliceTrainingService policeTrainingService;

    @GetMapping("trainDynamic")
    public CommonResult trainDynamic(){
	List<String> modelNameList = trainingModelService.getTrainingModelNameForPrisonAdmin();
	return CommonResult.success(modelNameList);
    }

    @GetMapping("dynamic/query")
    public CommonResult dynamicQuery(@AuthenticationPrincipal AccountUserDetails account,
				     @RequestBody PagingQueryParamForTrainingDynamic param){
	long account_id = account.getId();
	long prison_id = prisonAdminService.getPrisonIdByAccountId(account_id);
	PageHelper.startPage(param.getPageNum(), param.getPageSize());
	List<TrainingDynamicVO> tList = policeTrainingService.queryTrainingDynamicForPrisonAdmin(param.getPolice(),param.getModelName(),prison_id);
	PageInfo<TrainingDynamicVO> tPageInfo = new PageInfo<>(tList);
	PageResult<TrainingDynamicVO> result =  new PageResult<>((int)tPageInfo.getTotal(), tList);
	return CommonResult.success(result);
    }
}
