package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.PrisonAdmin;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
public interface PrisonAdminService {

    PrisonAdmin add(String nickname, long accountId, long prisonId);

    PrisonAdmin getByAccountId(long accountId);

    List<PrisonAdmin> queryPrisonAdmin(String query);

    void deleteById(List<Long> idList);
}
