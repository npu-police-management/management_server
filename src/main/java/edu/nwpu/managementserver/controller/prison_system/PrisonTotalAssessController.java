package edu.nwpu.managementserver.controller.prison_system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.nwpu.managementserver.constant.FormatConstant;
import edu.nwpu.managementserver.domain.TotalAssess;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.PagingQueryParam;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.service.PrisonAdminService;
import edu.nwpu.managementserver.service.TotalAssessService;
import edu.nwpu.managementserver.util.ConvertStringToIntArrayUtils;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.TotalAssessUseByPrisonAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static edu.nwpu.managementserver.constant.FormatConstant.FMT;
import static edu.nwpu.managementserver.util.ResultConversionUtil.*;

/**
 * @author GengXuelong
 * @version 1.0
 * @Mail 3349495429@qq.com
 * @time 2023/2/8 15:39
 * @className PrisonTotalAssessController
 * @description:
 */
@RestController
@RequestMapping("/api/backstage-management-service/prison")
public class PrisonTotalAssessController {

    @Autowired
    private TotalAssessService totalAssessService;
    @Autowired
    private PrisonAdminService prisonAdminService;
    @Autowired
    private PoliceService policeService;

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     监所系统通过模糊拆查询查到本监所警员的综合评估结果
     *     模糊范围： 在本监所警员范围的前提下：
     *     对警员姓名 警员账号 和 结果描述中模糊
     */
    @GetMapping("totalAssess")
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    public CommonResult totalAssessQuery(@AuthenticationPrincipal AccountUserDetails account,
                                    PagingQueryParam param){
        long account_id = account.getId();
        Function<TotalAssess,TotalAssessUseByPrisonAdminVO>  mapper = totalAssess -> new TotalAssessUseByPrisonAdminVO(
                totalAssess.getId()+"",
                policeService.getPoliceById(totalAssess.getPoliceId()).getName(),
                ConvertStringToIntArrayUtils.convertStringToIntArray(totalAssess.getMentalPercentList()),
                resToStr(totalAssess.getResult()),
                totalAssess.getDescription(),
                totalAssess.getCreateTime().format(FMT),
                totalAssess.getUpdateTime().format(FMT));
        long prison_id = prisonAdminService.getPrisonIdByAccountId(account_id);
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<TotalAssess> tList = totalAssessService.queryTotalAssessLikelyByPrisonAdmin(param.getQuery(),prison_id);
        PageInfo<TotalAssess> tPageInfo = new PageInfo<>(tList);
        PageResult<TotalAssessUseByPrisonAdminVO> result =  new PageResult<>((int)tPageInfo.getTotal(), tList.stream().map(mapper).toList());
        return CommonResult.success(result);
    }

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     通过id数组删除警员综合评估结果
     */
    @DeleteMapping("totalAssess")
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    public CommonResult deleteTotalAssess(@RequestBody Map<String,Object> map){
        ArrayList<?> strings = (ArrayList<?>) map.get("idList");
        long[] longs = new long[strings.size()];
        for (int i = 0; i <strings.size(); i++) {
            longs[i]=  Long.parseLong((String) strings.get(i));
        }
        totalAssessService.deleteByIdList(longs);
        return CommonResult.success();
    }
}
