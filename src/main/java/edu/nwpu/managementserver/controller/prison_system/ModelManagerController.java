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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *      获得超级管理员开启了的训练模型的列表
     *
     */
    @GetMapping("model")
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    public CommonResult modelVOList(PagingQueryParam pagingQueryParam){
        Function<TrainingModel,TrainingModelVO>  mapper = (trainingModel)->new TrainingModelVO(trainingModel.getId()+"",trainingModel.getName(),trainingModel.getDescription(),false,trainingModel.getPriority());
	PageResult<TrainingModelVO> pageResult = PageTransformUtil.toViewPage(
		pagingQueryParam,
		trainingModelService::getTrainingModelForPrisonAdmin,
		mapper
	);
	return CommonResult.success(pageResult);
    }


    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *      功能： 让监所管理员对警员打开活关闭训练模型
     *     以restful模式传入训练模型的id(String形式传，避免精度丢失，其实是long)，在请求体中传入
     *     enable字段决定是打开该模型还是关闭该模型
     *     监所管理员的身份识别由后端权限系统完成，无需传入身份识别参数
     *
     */
    @PutMapping("/model/{id}")
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    public CommonResult modifyModel(@PathVariable("id")long modelId,@RequestBody Map<String,Object> map,
                                    @AuthenticationPrincipal AccountUserDetails account){
        boolean enable = (Boolean)map.get("enable");
        long account_id = account.getId();
        long prison_id = prisonAdminService.getPrisonIdByAccountId(account_id);
        boolean exist = prisonModelService.exist(modelId, prison_id);
        System.out.println(exist+"  "+enable);
        if(enable && !exist){
            prisonModelService.addOne(new PrisonModel(SnowflakeIdUtil.nextId(),prison_id,modelId));
        }else if(!enable&&exist){
            prisonModelService.deleteOne(prison_id,modelId);
        }
        return CommonResult.success();
    }
}
