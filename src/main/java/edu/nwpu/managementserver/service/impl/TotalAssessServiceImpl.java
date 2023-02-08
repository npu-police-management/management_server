package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.domain.TotalAssess;
import edu.nwpu.managementserver.mapper.TotalAssessMapper;
import edu.nwpu.managementserver.service.TotalAssessService;
import edu.nwpu.managementserver.vo.TotalAssessForPoliceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 18:21
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.impl.TotalAssessServiceImpl
 * @description:
 */
@Service
public class TotalAssessServiceImpl implements TotalAssessService {
    @Autowired
    private TotalAssessMapper totalAssessMapper;
    @Override
    public TotalAssessForPoliceVO getLastTotalAssessForPolice(Long id) {
	TotalAssess totalAssess =  totalAssessMapper.getLastTotalAssessForPolice(id);
	int[] ints = new int[6];
	String intsString = totalAssess.getMentalPercentList();
	intsString = intsString.substring(1,intsString.length()-1);
        String[] split = intsString.split(",");
        for (int i = 0; i < 6; i++) {
            ints[0] =Integer.parseInt(split[0]);
        }
         return new TotalAssessForPoliceVO(totalAssess.getId(), ints, totalAssess.getResult(), totalAssess.getDescription(), totalAssess.getCreateTime().toString(), totalAssess.getUpdateTime().toString());
    }
}
