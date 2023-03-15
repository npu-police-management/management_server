package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.TotalAssess;
import edu.nwpu.managementserver.dto.TotalAssessDTO;
import edu.nwpu.managementserver.vo.TotalAssessForPoliceVO;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 18:21
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.TotalAssessServcie
 * @description:
 */
public interface TotalAssessService {
    TotalAssessForPoliceVO getLastTotalAssessForPolice(Long id);

    List<TotalAssess> queryTotalAssessLikelyByPrisonAdmin(String query, long prisonId);

    void deleteByIdList(long[] longs);

    List<TotalAssessDTO> getAssessByPrisonId(Long prisonId);

    void addResult(long policeId,
                   String result);
}
