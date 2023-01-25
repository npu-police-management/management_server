package com.nwpu.managementserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
@Data
public class AddPrisonParam {

    @NotBlank
    private String prisonName;
}
