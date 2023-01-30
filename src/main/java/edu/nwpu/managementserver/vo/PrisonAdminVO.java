package edu.nwpu.managementserver.vo;

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
public class PrisonAdminVO {

    private String id;

    private String nickname;

    private String accountNumber;

    private String prisonName;
}
