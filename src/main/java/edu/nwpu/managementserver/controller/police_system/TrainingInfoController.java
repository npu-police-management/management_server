package edu.nwpu.managementserver.controller.police_system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.nwpu.managementserver.domain.Police;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.PagingQueryParam;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.service.TotalAssessService;
import edu.nwpu.managementserver.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 17:41
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.controller.police_system.TrainingInfoController
 * @description:
 */
@RestController
@RequestMapping("/api/backstage-management-service/police")
public class TrainingInfoController {
    @Autowired
    private PoliceService policeService;
    @Autowired
    private PoliceTrainingService policeTrainingService;
    @Autowired
    private TotalAssessService totalAssessService;
    
    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     警员系统查询自己关于某个模型的训练记录，模型采用全模糊查询
     */
    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("query")
    public CommonResult TrainingInfoQuery(PagingQueryParam param, @AuthenticationPrincipal AccountUserDetails account){
	long account_id = account.getId();
	Police police = policeService.getPoliceByAccountId(account_id);
	PageHelper.startPage(param.getPageNum(), param.getPageSize());
	List<TrainingDynamicForPoliceVO> tList = policeTrainingService.getTrainingDynamicListForPolice(police.getId(),param.getQuery());
	PageInfo<TrainingDynamicForPoliceVO> tPageInfo = new PageInfo<>(tList);
	PageResult<TrainingDynamicForPoliceVO> result =  new PageResult<>((int)tPageInfo.getTotal(), tList);
	return CommonResult.success(result);
    }

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     警员系统获取自己的综合评估数据
     */
    @PreAuthorize("hasAuthority('Police')")
    @GetMapping("/totalResult")
    public CommonResult totalResult( @AuthenticationPrincipal AccountUserDetails account){
	long account_id = account.getId();
	Police police = policeService.getPoliceByAccountId(account_id);
	TotalAssessForPoliceVO totalAssessForPoliceVO = totalAssessService.getLastTotalAssessForPolice(police.getId());
	return CommonResult.success(totalAssessForPoliceVO);
    }
}
