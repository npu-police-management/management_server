package edu.nwpu.managementserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/31
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordChangeParam {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
