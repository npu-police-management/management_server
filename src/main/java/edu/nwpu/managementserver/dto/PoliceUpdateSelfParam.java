package edu.nwpu.managementserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 22:28
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.dto.PoliceUpdateSelfParam
 * @description:
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PoliceUpdateSelfParam {
    private String name;
    private String imageUrl;
}
