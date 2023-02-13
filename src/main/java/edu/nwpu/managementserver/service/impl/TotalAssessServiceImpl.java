package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.domain.TotalAssess;
import edu.nwpu.managementserver.mapper.TotalAssessMapper;
import edu.nwpu.managementserver.service.TotalAssessService;
import edu.nwpu.managementserver.util.CommonUtils;
import edu.nwpu.managementserver.vo.TotalAssessForPoliceVO;
import edu.nwpu.managementserver.vo.TotalAssessUseByPrisonAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(totalAssess==null) return null;
        String intsString = totalAssess.getMentalPercentList();
        int[] ints = CommonUtils.convertStringToIntArray(intsString);
        return new TotalAssessForPoliceVO(totalAssess.getId(), ints, totalAssess.getResult(), totalAssess.getDescription(), totalAssess.getCreateTime().toString(), totalAssess.getUpdateTime().toString());
    }

    @Override
    public List<TotalAssess> queryTotalAssessLikelyByPrisonAdmin(String query, long prisonId) {
        return totalAssessMapper.queryTotalAssessLikelyByPrisonAdmin(query,prisonId);
    }

    @Override
    public void deleteByIdList(long[] longs) {
        totalAssessMapper.deleteByIdList(longs);
    }
}
