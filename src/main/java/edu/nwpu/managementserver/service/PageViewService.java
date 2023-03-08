package edu.nwpu.managementserver.service;

import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/3/5
 */
public interface PageViewService {


    void saveOneView(String info);

    Integer getTodayViews();

    List<Integer> getWeekViews();
}
