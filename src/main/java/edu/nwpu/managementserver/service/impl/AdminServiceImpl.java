package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.constant.CodeEnum;
import edu.nwpu.managementserver.domain.Admin;
import edu.nwpu.managementserver.exception.BusinessException;
import edu.nwpu.managementserver.mapper.AdminMapper;
import edu.nwpu.managementserver.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {

        this.adminMapper = adminMapper;
    }

    @Override
    public Admin getByAccountId(long accountId) {

        Admin admin = adminMapper.getByAccountId(accountId);
        if (admin == null) {
            throw new BusinessException(CodeEnum.NotFound, "找不到该账户的管理员信息");
        }
        return admin;
    }
}
