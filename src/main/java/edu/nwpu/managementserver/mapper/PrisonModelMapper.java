package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.domain.PrisonModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
* @author ANO4679
* @description 针对表【prison_model】的数据库操作Mapper
* @createDate 2023-01-20 19:04:13
* @Entity com.nwpu.managementserver.domain.PrisonModel
*/
@Mapper
public interface PrisonModelMapper {
    List<Long> getModelIdListByPrisonId(Long prisonId);
    int getModelIdSizeByPrisonId(Long prisonId);
    int addRecord(PrisonModel prisonModel);
    int deleteRecord(@Value("prisonId")Long prisonId,@Value("modelId")Long modelId);
}




