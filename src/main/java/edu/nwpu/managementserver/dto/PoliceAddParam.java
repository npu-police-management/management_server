package edu.nwpu.managementserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 14:59
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.dto.PoliceAddParam
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceAddParam {
    private String name;
    private String imageUrl;
    private String policeCode;
}
