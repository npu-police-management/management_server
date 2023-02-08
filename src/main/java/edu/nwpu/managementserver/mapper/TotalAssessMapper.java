package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.domain.TotalAssess;
import edu.nwpu.managementserver.vo.TotalAssessForPoliceVO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ANO4679
* @description 针对表【total_assess】的数据库操作Mapper
* @createDate 2023-01-20 19:07:44
* @Entity com.nwpu.managementserver.domain.TotalAssess
*/
@Mapper
public interface TotalAssessMapper {

    TotalAssess getLastTotalAssessForPolice(Long prisonId);
}




