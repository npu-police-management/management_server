package edu.nwpu.managementserver.dto;

import edu.nwpu.managementserver.util.RsaDecryptUtil;
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

    public PasswordChangeParam getDecryptParam(String privateKey) {
        return new PasswordChangeParam(
                RsaDecryptUtil.decrypt(privateKey, oldPassword),
                RsaDecryptUtil.decrypt(privateKey, newPassword));
    }
}
