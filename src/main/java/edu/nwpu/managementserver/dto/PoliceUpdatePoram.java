package edu.nwpu.managementserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 17:00
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.dto.PoliceRemoveParam
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceUpdatePoram {
    private String name;
    private String prisonName;
    private String imageUrl;
}
