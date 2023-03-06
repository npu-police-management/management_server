package edu.nwpu.managementserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingQueryParam {

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    private String query;
}
