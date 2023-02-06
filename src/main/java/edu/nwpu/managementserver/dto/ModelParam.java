package edu.nwpu.managementserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/2/5
 */
@Data
public class ModelParam {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private Boolean enable;

    private Integer priority;
}
