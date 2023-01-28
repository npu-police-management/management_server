package com.nwpu.managementserver.service;

import com.nwpu.managementserver.domain.Admin;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
public interface AdminService {

    Admin getByAccountId(long accountId);
}
