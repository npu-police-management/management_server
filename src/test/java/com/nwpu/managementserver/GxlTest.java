package com.nwpu.managementserver;

import com.nwpu.managementserver.domain.Police;
import com.nwpu.managementserver.domain.Prison;
import com.nwpu.managementserver.mapper.PoliceMapper;
import com.nwpu.managementserver.mapper.PrisonAdminMapper;
import com.nwpu.managementserver.mapper.PrisonMapper;
import com.nwpu.managementserver.service.PoliceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
    PrisonAdminMapper prisonAdminMapper ;
    @Test
    public void test(){
        Police police = new Police(43242342L,"fd龙llll","http://",1L,1L);
        Police police1 = new Police(432423421L,"耿雪dsfd龙","http://",1L,1L);
        Police police2 = new Police(43223421L,"耿雪dsfd龙","http://",1L,1L);
        Police police3 = new Police(4423421L,"耿雪dsfd龙","http://",1L,1L);
        Police police5 = new Police(43242321L,"耿雪dsfd龙","http://",1L,1L);
        Police police4 = new Police(43223423421L,"耿雪dsfd龙","http://",1L,1L);
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
        System.out.println(policeMapper.getPoliceByAccountId(1069661572999151616L));
    }

}
