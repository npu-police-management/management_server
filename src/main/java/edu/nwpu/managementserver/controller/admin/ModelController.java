package edu.nwpu.managementserver.controller.admin;

import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.dto.IdListParam;
import edu.nwpu.managementserver.dto.ModelParam;
import edu.nwpu.managementserver.dto.PagingQueryParam;
import edu.nwpu.managementserver.service.TrainingModelService;
import edu.nwpu.managementserver.util.PageTransformUtil;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.TrainingModelVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiayi Zhu
 * 2023/2/5
 */
@RestController
@RequestMapping("/api/psychology-service/model")
public class ModelController {

    @Autowired
    private TrainingModelService trainingModelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("")
    public CommonResult addModel(@Valid @RequestBody ModelParam param) {

        TrainingModel model = trainingModelService.addModel(param);
        return CommonResult.success(
                new TrainingModelVO(
                        model.getId().toString(),
                        model.getName(),
                        model.getDescription(),
                        model.getEnable(),
                        model.getPriority()
                )
        );
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{modelId}")
    public CommonResult updateModel(@PathVariable String modelId, @RequestBody ModelParam param) {

        trainingModelService.updateModel(Long.parseLong(modelId), param);
        return CommonResult.success();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("")
    public CommonResult deleteAllById(@RequestBody IdListParam param) {

        trainingModelService.deleteById(param.getIdList());
        return CommonResult.success();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("")
    public CommonResult getAllByQuery(@Valid PagingQueryParam param) {

        PageResult<TrainingModelVO> result = PageTransformUtil.toViewPage(
                param,
                trainingModelService::queryTrainingModel,
                trainingModel -> new TrainingModelVO(
                        trainingModel.getId().toString(),
                        trainingModel.getName(),
                        trainingModel.getDescription(),
                        trainingModel.getEnable(),
                        trainingModel.getPriority()
                )
        );
        return CommonResult.success(result);
    }
}
