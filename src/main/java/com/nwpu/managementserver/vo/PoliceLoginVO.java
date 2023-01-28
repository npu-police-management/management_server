package com.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * 2023/1/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceLoginVO {

    private String id;

    private String name;

    private String imageUrl;

    private String prisonName;

    /**
     * 是否在训练
     */
    private Boolean training;
}
