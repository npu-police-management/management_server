package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.Admin;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
public interface AdminService {

    Admin getByAccountId(long accountId);
}
