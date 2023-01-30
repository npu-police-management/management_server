package edu.nwpu.managementserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/31 0:46
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.dto.PagingQueryForPrisonAdminParam
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingQueryForPrisonAdminParam {
    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    private String query;

    private long prisonId=-1;

    public PagingQueryForPrisonAdminParam(PagingQueryParam param, long prison_id) {
        this.pageNum = param.getPageNum();
        this.pageSize = param.getPageSize();
        this.query = param.getQuery();
        this.prisonId = prison_id;
    }
}
