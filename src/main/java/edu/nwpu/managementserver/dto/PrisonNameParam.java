package edu.nwpu.managementserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
@Data
public class PrisonNameParam {

    @NotBlank
    private String prisonName;
}
