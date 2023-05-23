package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 20:46
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.vo.PrisonAdiminMainPageVO
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrisonAdminMainPageVO {
    private String workingModeNumber;
    private String finishTrainTimeDaily;
    private String abnormalCount;
    private String finishTrainTimeWeekly;
}
