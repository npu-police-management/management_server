package edu.nwpu.managementserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
@Data
@AllArgsConstructor
public class PAdminAccountDTO {

    private Long id;

    private String nickname;

    private String accountNumber;
}
