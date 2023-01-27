package com.nwpu.managementserver.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nwpu.managementserver.dto.PagingQueryParam;
import com.nwpu.managementserver.vo.PageResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author Jiayi Zhu
 * 2023/1/27
 */
public class PageTransformUtil {

    /**
     * change entity page with page-helper to view page
     *
     * @param param page param
     * @param query the selection statement
     * @param mapper mapping from T to R
     * @return PageInfo with view object
     * @param <T> entity object
     * @param <R> view object
     */
    public static <T, R> PageResult<R> toViewPage(PagingQueryParam param,
                                                Function<String, List<T>> query,
                                                Function<T, R> mapper) {

        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<T> tList = query.apply(param.getQuery());
        PageInfo<T> tPageInfo = new PageInfo<>(tList);
        return new PageResult<>(
                tPageInfo.getPages(),
                tList.stream().map(mapper).toList()
        );
    }

}
