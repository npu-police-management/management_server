package edu.nwpu.managementserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/7 17:12
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.dto.PagingQueryParamForTrainingDynamic
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingQueryParamForTrainingDynamic {
    private int pageNum;
    private int pageSize;
    private String police;
    private String modelName;
}
