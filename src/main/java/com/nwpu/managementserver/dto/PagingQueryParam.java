package com.nwpu.managementserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
@Data
public class PagingQueryParam {

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    private String query;
}
