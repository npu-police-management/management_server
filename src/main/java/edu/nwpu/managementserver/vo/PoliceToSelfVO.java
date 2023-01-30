package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 20:33
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.vo.PoliceToSelfVO
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceToSelfVO {
    private String id;
    private String name;
    private String accountNumber;
    private String imageUrl;
    private String prisonName;
}
