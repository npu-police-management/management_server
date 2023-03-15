package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.domain.TotalAssess;
import edu.nwpu.managementserver.dto.TotalAssessDTO;
import edu.nwpu.managementserver.mapper.TotalAssessMapper;
import edu.nwpu.managementserver.service.TotalAssessService;
import edu.nwpu.managementserver.util.ConvertStringToIntArrayUtils;
import edu.nwpu.managementserver.util.SnowflakeIdUtil;
import edu.nwpu.managementserver.vo.TotalAssessForPoliceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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
        int[] ints = ConvertStringToIntArrayUtils.convertStringToIntArray(intsString);
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

    @Override
    public List<TotalAssessDTO> getAssessByPrisonId(Long prisonId) {

        var r = totalAssessMapper.getAllByPrisonId(prisonId);
        System.out.println("-----------------------");
        for (var i : r) {
            System.out.println(i.getName());
            System.out.println(i.getDescription());
            System.out.println(i.getMentalPercentList());
        }
        return r;
    }

    @Override
    public void addResult(long policeId, String result) {

        Random r = new Random();
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < 5; i++) {
            s.append(Math.abs(r.nextInt() % 100)).append(",");
        }
        s.append(Math.abs(r.nextInt() % 100)).append("]");
        TotalAssess totalAssess = new TotalAssess(
                SnowflakeIdUtil.nextId(),
                policeId,
                s.toString(),
                true,
                result,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        totalAssessMapper.insert(totalAssess);
    }
}
