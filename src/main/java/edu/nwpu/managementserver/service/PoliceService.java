package edu.nwpu.managementserver.service;


import edu.nwpu.managementserver.domain.Police;

import java.util.List;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 11:35
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.service.PoliceService
 * @description:
 */
public interface PoliceService {
    List<Police> queryList(String query, long prison_id);

    void add(Police police);

    long getPrisonIdByAccountId(long id);

    void deleteList(List<Long> idList);

    Police getPoliceById(long id);

    void update(Police police);

    Police getPoliceByAccountId(Long account_id);
}
