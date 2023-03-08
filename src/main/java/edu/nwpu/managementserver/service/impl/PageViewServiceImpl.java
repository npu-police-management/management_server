package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.service.PageViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/3/5
 */
@Service
public class PageViewServiceImpl implements PageViewService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void saveOneView(String info) {

        String currentDate = LocalDate.now().toString();

        stringRedisTemplate.opsForSet().add(currentDate, info);
    }

    @Override
    public Integer getTodayViews() {

        String currentDate = LocalDate.now().toString();

        Long views = stringRedisTemplate.opsForSet().size(currentDate);
        return views == null ? 0 : views.intValue();
    }

    @Override
    public List<Integer> getWeekViews() {

        List<LocalDate> localDateList = new ArrayList<>();
        int weekSize = 7;
        for (int i = weekSize - 1; i >= 0; i--) {
            localDateList.add(LocalDate.now().minusDays(i));
        }

        return localDateList.stream().map(
                date -> {
                    Long value = stringRedisTemplate.opsForSet().size(date.toString());
                    return value == null ? 0 : value.intValue();
                }
        ).toList();
    }

}
