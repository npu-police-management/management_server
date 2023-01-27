package com.nwpu.managementserver.service;

import com.nwpu.managementserver.domain.PrisonAdmin;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
public interface PrisonAdminService {

    PrisonAdmin add(String nickname, long accountId, long prisonId);

    PrisonAdmin getByAccountId(long accountId);
}
