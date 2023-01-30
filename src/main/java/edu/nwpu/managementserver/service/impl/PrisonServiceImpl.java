package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.domain.Prison;
import edu.nwpu.managementserver.exception.BusinessException;
import edu.nwpu.managementserver.mapper.AccountMapper;
import edu.nwpu.managementserver.mapper.PrisonAdminMapper;
import edu.nwpu.managementserver.mapper.PrisonMapper;
import edu.nwpu.managementserver.service.PrisonService;
import edu.nwpu.managementserver.util.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.nwpu.managementserver.constant.CodeEnum.NotFound;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
@Service
public class PrisonServiceImpl implements PrisonService {

    private PrisonMapper prisonMapper;

    private PrisonAdminMapper prisonAdminMapper;

    private AccountMapper accountMapper;

    @Autowired
    public void setPrisonMapper(PrisonMapper prisonMapper) {

        this.prisonMapper = prisonMapper;
    }

    @Autowired
    public void setPrisonAdminMapper(PrisonAdminMapper prisonAdminMapper) {

        this.prisonAdminMapper = prisonAdminMapper;
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {

        this.accountMapper = accountMapper;
    }

    @Override
    public Prison addPrison(String prisonName) {

        long id = SnowflakeIdUtil.nextId();
        Prison prison = new Prison(id, prisonName);
        prisonMapper.insert(prison);
        return prison;
    }

    @Override
    public Prison getPrisonByName(String prisonName) {

        Prison prison = prisonMapper.getByName(prisonName);
        if (prison == null) {
            throw new BusinessException(NotFound, "该监所不存在");
        }
        return prison;
    }

    @Override
    public Prison getPrisonById(long id) {

        Prison prison = prisonMapper.getById(id);
        if (prison == null) {
            throw new BusinessException(NotFound, "该监所不存在");
        }
        return prison;
    }

    @Override
    public List<Prison> queryPrison(String query) {

        return prisonMapper.getAllByNameLike(query);
    }

    @Override
    public void deleteById(List<Long> idList) {

        accountMapper.deleteByPrisonId(idList);
        prisonAdminMapper.deleteByPrisonId(idList);
        prisonMapper.deleteById(idList);
    }

    @Override
    public List<String> getPrisonNameList() {

        return prisonMapper.getPrisonNameList();
    }
}
