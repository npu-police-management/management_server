package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.Prison;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/1/25
 */
public interface PrisonService {

    Prison addPrison(String prisonName);

    Prison getPrisonByName(String prisonName);

    Prison getPrisonById(long id);

    List<Prison> queryPrison(String query);

    void deleteById(List<Long> idList);

    List<String> getPrisonNameList();

}
