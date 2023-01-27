package com.nwpu.managementserver.service.impl;

import com.nwpu.managementserver.domain.PrisonAdmin;
import com.nwpu.managementserver.exception.BusinessException;
import com.nwpu.managementserver.mapper.PrisonAdminMapper;
import com.nwpu.managementserver.service.PrisonAdminService;
import com.nwpu.managementserver.util.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nwpu.managementserver.constant.CodeEnum.CreationError;
import static com.nwpu.managementserver.constant.CodeEnum.NotFound;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
@Service
public class PrisonAdminServiceImpl implements PrisonAdminService {

    private PrisonAdminMapper prisonAdminMapper;

    @Autowired
    public void setPrisonAdminMapper(PrisonAdminMapper prisonAdminMapper) {

        this.prisonAdminMapper = prisonAdminMapper;
    }

    @Override
    public PrisonAdmin add(String nickname, long accountId, long prisonId) {

        PrisonAdmin oldPrisonAdmin = prisonAdminMapper.getByAccountId(accountId);
        if (oldPrisonAdmin != null) {
            throw new BusinessException(CreationError, "此账号对应的监所管理员信息已存在");
        }
        long id = SnowflakeIdUtil.nextId();
        PrisonAdmin prisonAdmin = new PrisonAdmin(id, nickname, accountId, prisonId);
        prisonAdminMapper.insert(prisonAdmin);
        return prisonAdmin;
    }

    @Override
    public PrisonAdmin getByAccountId(long accountId) {

        PrisonAdmin prisonAdmin = prisonAdminMapper.getByAccountId(accountId);
        if (prisonAdmin == null) {
            throw new BusinessException(NotFound, "找不到此账号对应的监所管理员信息");
        }
        return prisonAdmin;
    }
}
