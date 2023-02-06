package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.mapper.AccessRecordMapper;
import edu.nwpu.managementserver.service.AccessRecordService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/2/6 19:57
 * @email 3349495429@qq.com
 * @className edu.nwpu.managementserver.service.impl.AccessRecordServiceImpl
 * @description:
 */
public class AccessRecordServiceImpl implements AccessRecordService {
    @Autowired
    private AccessRecordMapper accessRecordMapper;
    @Override
    public int getNumberTodayAccess(long prisonId) {
	return accessRecordMapper.getNumberTodayAccess(prisonId);
    }
}
