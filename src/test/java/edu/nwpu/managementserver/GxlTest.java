package edu.nwpu.managementserver;

import edu.nwpu.managementserver.domain.Police;
import edu.nwpu.managementserver.domain.PrisonModel;
import edu.nwpu.managementserver.domain.TrainingModel;
import edu.nwpu.managementserver.dto.PagingQueryForPrisonAdminParam;
import edu.nwpu.managementserver.mapper.*;
import edu.nwpu.managementserver.service.AccountService;
import edu.nwpu.managementserver.service.PoliceService;
import edu.nwpu.managementserver.util.PageTransformUtil;
import edu.nwpu.managementserver.util.RsaDecryptUtil;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.PoliceVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Function;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 11:51
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.GxlTest
 * @description:
 */
@SpringBootTest
public class GxlTest {
    @Autowired
    PoliceMapper policeMapper;
    @Autowired
    PrisonMapper prisonMapper;
    @Autowired
    AccountService accountService;

    @Autowired
    PrisonAdminMapper prisonAdminMapper ;
    @Autowired
    PoliceService policeService;
    @Test
    public void test() {
        Police police = new Police(43242342L, "fd龙llll", "http://", 1L, 1L);
        Police police1 = new Police(432423421L, "耿雪dsfd龙", "http://", 1L, 1L);
        Police police2 = new Police(43223421L, "耿雪dsfd龙", "http://", 1L, 1L);
        Police police3 = new Police(4423421L, "耿雪dsfd龙", "http://", 1L, 1L);
        Police police5 = new Police(43242321L, "耿雪dsfd龙", "http://", 1L, 1L);
        Police police4 = new Police(43223423421L, "耿雪dsfd龙", "http://", 1L, 1L);
//        Police police2 = new Police(432423422L,"耿dsfd龙","http://",1L,1L);
//        System.out.println(policeMapper.save(police));
//        System.out.println(policeMapper.save(police1));
//        System.out.println(policeMapper.save(police2));
//        System.out.println(policeMapper.getList());
//        System.out.println(policeMapper.getPoliceListByNameLike(""));
//        System.out.println(policeMapper.save(police));
//        System.out.println(policeMapper.save(police1));
//        System.out.println(policeMapper.save(police2));
//        System.out.println(policeMapper.save(police3));
//        System.out.println(policeMapper.save(police4));
//        System.out.println(policeMapper.save(police5));
//        System.out.println(prisonAdminMapper.getPrisonIdByAccountId(12));
//        System.out.println(policeMapper.getPoliceByAccountId(1069661572999151616L));
//        System.out.println(prisonAdminMapper.getByAccountId(110L));
        System.out.println(accountService.getById(110L));
    }
    @Test
    public void tedfsfst(){
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMdBbmmkALx1oql+F3fwA986OPIJzD1ZdMlFVecdi4fcQ7iTR8cDqelJtPUgOA052KAwQwM2ePAn42j2JxtUm5qOUe/DgfxDXFMm/Id+B5g6HuejYei8BdHPhM9wO7o9skN4WGAX3Rmf5Y5HI1H4tI1HTDrE4Txz4uSinV245VfZAgMBAAECgYEAlwU4skjjZfOi/ePb1cPMq3xtfMWAT19l7MbofOsfvjFsApFebbCRL4f153QsB5o4XfE2WVEICL2y1vL6YPSXmg42uM2numDgjGsQxy69KjIq9fz5jiLrKE+dzb0BRIzHOjK8YE71xHxqMvrAw4xlP8SnNA9OjurcJa2H6tzc3oECQQDubw9f6FnlYb91pTdlpuJqteLCDVgCiBSEwmX6ZSMvKv1B5g5PIFcI9Rs/pBrQqmQW09fCOPPkXsTzomAVoCONAkEA1e909gxLx628vVqEa0+izm9zQSb9WAQCqyp9xb9+NHDkMp6V1qRVe4cbzSLzTkGYLAXNuPcK9HlHAjy1yibsfQJAIw6pUAy1ywhIQl/8eTHEcLQ1lxqnKhggDDUZEWv1dGX5vsq4p+G1ale/neffGXc0KDcVrjT5h0Z35uqk/gkBsQJAIkM3eDB/8nrgv3K/wm/+oxE2ZDjaWj5KLx+fBQymKb/qzDCZzsaC6kW5LiFq1h2rQWGskPL/zieeN72BX97cAQJAVp8hiBdhyJR+/s9lfP0MSfzdlFFeHKYJXM6VVgbHpGhNa8XeMLCSmLr07Vl7v0ffBzOX6u+ChYAvVNRnwCVogQ==";
        String password = "IjX0J6fkPyThi8PivZuu3aYNiF/DJXtzF3oybhQYPOumQF31CWuZlC08fxNnPGOqrgXTI2xQqssQ9pFpBCMP1maFL2bzHeL6Pgq9xfcuSvlyvUSgfxW+A2+JdigmWyC5BqOW55frw9gzTH+ENJbLo5fVuPXPzb4cdhEWUbEA/EI=";
        String decrypt = RsaDecryptUtil.decrypt(privateKey, password);
        System.out.println(decrypt);
    }
    @Test
    public void tesfdsfs(){
        PagingQueryForPrisonAdminParam pagingQueryForPrisonAdminParam = new PagingQueryForPrisonAdminParam(1,2,"",1L);
        Function<Police,PoliceVO> mapper = police->new PoliceVO(police.getId()+"",police.getName(),accountService.getById(police.getAccountId()).getAccountNumber(),police.getImageUrl());
        PageResult<PoliceVO> policeVOPageResult = PageTransformUtil.toViewPage(pagingQueryForPrisonAdminParam, policeService::queryList, mapper);
        System.out.println(policeVOPageResult.getTotal()+""+policeVOPageResult.getList());
    }



    @Autowired
    PrisonModelMapper prisonModelMapper;
    @Test
    public void testPrisonModelMapper(){
        List<Long> modeIdListByPrisonId = prisonModelMapper.getModelIdListByPrisonId(2L);
        System.out.println(modeIdListByPrisonId);
//        System.out.println(prisonModelMapper.deleteRecord(2,3));
//        System.out.println(prisonModelMapper.addRecord(new PrisonModel(3L,2L,3L)));
//        System.out.println(prisonModelMapper.getModelIdSizeByPrisonId(2L));
    }
    @Autowired
    AccessRecordMapper accessRecordMapper;
    @Test
    public void testAccessRecordMapper(){
        System.out.println(accessRecordMapper.getNumberTodayAccess(2));
    }
    @Autowired
    PoliceTrainingMapper policeTrainingMapper;
    @Test
    public void testPoliceTrainingMapper(){
//        System.out.println(policeTrainingMapper.getNumberTodayFinish(2));
//        System.out.println(policeTrainingMapper.getThreeDate(2));
        System.out.println(policeTrainingMapper.getWeeklyStatus(2));
    }
    @Autowired
    TrainingModelMapper trainingModelMapper;
    @Test
    public void testTrainingModel(){
        List<TrainingModel> modelListForPrisonAdmin = trainingModelMapper.getModelListForPrisonAdmin();
        System.out.println(modelListForPrisonAdmin);
    }

}
