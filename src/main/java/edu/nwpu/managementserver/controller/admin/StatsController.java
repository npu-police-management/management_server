package edu.nwpu.managementserver.controller.admin;

import edu.nwpu.managementserver.constant.CodeEnum;
import edu.nwpu.managementserver.constant.FormatConstant;
import edu.nwpu.managementserver.exception.BusinessException;
import edu.nwpu.managementserver.service.PageViewService;
import edu.nwpu.managementserver.service.PoliceTrainingService;
import edu.nwpu.managementserver.service.TotalAssessService;
import edu.nwpu.managementserver.util.ConvertStringToIntArrayUtils;
import edu.nwpu.managementserver.util.ResultConversionUtil;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.StatsVO;
import edu.nwpu.managementserver.vo.TotalAssessUseByPrisonAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.nwpu.managementserver.constant.FormatConstant.FMT;
import static edu.nwpu.managementserver.util.ResultConversionUtil.*;

/**
 * @author Jiayi Zhu
 * 2023/3/5
 */
@RestController
@RequestMapping("/api")
public class StatsController {

    @Autowired
    private PoliceTrainingService policeTrainingService;

    @Autowired
    private PageViewService pageViewService;

    @Autowired
    private TotalAssessService totalAssessService;

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/backstage-management-service/admin/stats")
    public CommonResult getStats() {

        int accessCount = pageViewService.getTodayViews();
        int trainingCount = policeTrainingService.todayTrainingCounts();
        int weeklyTrainingCount = policeTrainingService.weekTrainingCounts();
        List<Integer> weeklyAccess = pageViewService.getWeekViews();
        StatsVO statsVO = new StatsVO(
                accessCount,
                trainingCount,
                weeklyTrainingCount,
                accessCount,
                weeklyAccess
        );
        return CommonResult.success(statsVO);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/psychology-service/assessment/{prisonId}")
    public CommonResult getResult(@PathVariable String prisonId) {

        if (!StringUtils.hasText(prisonId))
            throw new BusinessException(CodeEnum.RequestError, "PrisonId 为空");
        List<TotalAssessUseByPrisonAdminVO> totalAssessList
                = totalAssessService.getAssessByPrisonId(Long.parseLong(prisonId)).stream()
                .map(totalAssess -> new TotalAssessUseByPrisonAdminVO(
                        totalAssess.getId(),
                        totalAssess.getName(),
                        ConvertStringToIntArrayUtils.convertStringToIntArray(totalAssess.getMentalPercentList()),
                        resToStr(totalAssess.isResult()),
                        totalAssess.getDescription(),
                        totalAssess.getCreateTime().format(FMT),
                        totalAssess.getUpdateTime().format(FMT)
                )).toList();
        return CommonResult.success(totalAssessList);
    }
}
