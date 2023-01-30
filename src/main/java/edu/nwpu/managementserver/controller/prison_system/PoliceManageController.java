package edu.nwpu.managementserver.controller.prison_system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import edu.nwpu.managementserver.domain.Police;
import edu.nwpu.managementserver.domain.Prison;
import edu.nwpu.managementserver.dto.*;
import edu.nwpu.managementserver.exception.ManagementException;
import edu.nwpu.managementserver.service.AccountService;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.service.PrisonService;
import edu.nwpu.managementserver.util.PageTransformUtil;
import edu.nwpu.managementserver.util.SnowflakeIdUtil;
import edu.nwpu.managementserver.vo.CommonResult;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.PoliceVO;
import edu.nwpu.managementserver.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/29 21:35
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.controller.Prison.PoliceManageController
 * @description:
 */
@RestController
@RequestMapping("/api/backstage-management-service/prison")
public class PoliceManageController {
    @Autowired
    private PrisonService prisonService;
    @Autowired
    private PoliceService policeService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     得到所偶有监所的名字列表
     */
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    @GetMapping("/prison")
    public CommonResult prison(){
        List<String> prisonNameList = prisonService.getPrisonNameList();
        return CommonResult.success(prisonNameList);
    }


    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     分页查询警员信息
     */
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    @GetMapping("/police/query")
    public CommonResult query(@Valid PagingQueryParam param, @AuthenticationPrincipal AccountUserDetails account){
        System.out.println("================>进入query");
        //account = AccountUserDetails.of(new Account(2L,null,"",1));
        long account_id = account.getId();
        System.out.println("account_id--->"+account_id);
        long prison_id = policeService.getPrisonIdByAccountId(account_id);
        System.out.println("prison_id----->"+prison_id);
        PagingQueryForPrisonAdminParam pagingQueryForPrisonAdminParam = new PagingQueryForPrisonAdminParam(param,prison_id);
        Function<Police,PoliceVO> mapper =  police->new PoliceVO(police.getId()+"",police.getName(),(accountService.getById(police.getAccountId())).getAccountNumber(),police.getImageUrl());
        PageResult<PoliceVO> policeVOPageResult = PageTransformUtil.toViewPage(pagingQueryForPrisonAdminParam, policeService::queryList, mapper);
        return CommonResult.success(policeVOPageResult);

    }

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     添加一个新警员
     */
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    @PostMapping("police")
    public CommonResult save(@RequestBody @Valid PoliceAddParam policeAddParam,
                             @AuthenticationPrincipal AccountUserDetails account){
        //account = AccountUserDetails.of(new Account(2L,null,"",1));
        System.out.println(policeAddParam.getPoliceCode()+policeAddParam.getImageUrl()+policeAddParam.getName());
        System.out.println(account.getId()+account.getAccountNumber());
        try {
            long prison_id = policeService.getPrisonIdByAccountId(account.getId());
            Police police = new Police(SnowflakeIdUtil.nextId(),policeAddParam.getName(),policeAddParam.getImageUrl(),prison_id,null);
            AccountUserDetails add = accountService.add(policeAddParam.getPoliceCode(), policeAddParam.getPoliceCode(), 0, passwordEncoder::encode);
            police.setAccountId(add.getId());
            policeService.add(police);
            return CommonResult.success();
        } catch (ManagementException e) {
            return CommonResult.failure(e);
        }
    }

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     批量删除警员
     */
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    @DeleteMapping("/police")
    public CommonResult deletePoliceList(@RequestBody IdListParam param){
        System.out.println(param.getIdList());
        policeService.deleteList(param.getIdList());
        return CommonResult.success();
    }

    /**
     * @author GengXuelong
     * <p> 函数功能描述如下:
     * @description:
     *     监所管理员更新警员的数据
     */
    @PreAuthorize("hasAuthority('PrisonAdmin')")
    @PutMapping("/police/{id}")
    public CommonResult update(@RequestBody PoliceUpdateParam param, @PathVariable("id")long id){
        Police police = policeService.getPoliceById(id);
        police.setName(param.getName());
        police.setImageUrl(param.getImageUrl());
        String prisonName = param.getPrisonName();
        System.out.println(param.getPrisonName());
        if(prisonName!=null&&!prisonName.equals("")){
            Prison prisonByName = prisonService.getPrisonByName(param.getPrisonName());
            System.out.println(prisonByName);
            police.setPrisonId(prisonByName.getId());
        }
        policeService.update(police);
        return CommonResult.success();
    }



}
