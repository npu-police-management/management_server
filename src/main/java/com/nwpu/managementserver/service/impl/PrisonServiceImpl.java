package com.nwpu.managementserver.service.impl;

import com.nwpu.managementserver.domain.Prison;
import com.nwpu.managementserver.exception.BusinessException;
import com.nwpu.managementserver.mapper.PrisonMapper;
import com.nwpu.managementserver.service.PrisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nwpu.managementserver.constant.CodeEnum.NotFound;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
@Service
public class PrisonServiceImpl implements PrisonService {

    private PrisonMapper prisonMapper;

    @Autowired
    public void setPrisonMapper(PrisonMapper prisonMapper) {

        this.prisonMapper = prisonMapper;
    }

    @Override
    public void addPrison(long id, String prisonName) {

        Prison prison = new Prison(id, prisonName);
        prisonMapper.insert(prison);
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
}
