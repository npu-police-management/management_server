package com.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 11:17
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.vo.PoliceVO
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceVO {
    private long id;
    private String name;
    private String accountNumber;
    private String imageUrl;
}
