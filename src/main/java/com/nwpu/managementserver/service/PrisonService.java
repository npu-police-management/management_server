package com.nwpu.managementserver.service;

import com.nwpu.managementserver.domain.Prison;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
public interface PrisonService {

    void addPrison(long id, String prisonName);

    Prison getPrisonByName(String prisonName);

    Prison getPrisonById(long id);
}
