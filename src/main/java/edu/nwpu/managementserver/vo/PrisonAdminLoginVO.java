package edu.nwpu.managementserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * 2023/1/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrisonAdminLoginVO {

    private String id;

    private String nickname;

    private String prisonName;
}
