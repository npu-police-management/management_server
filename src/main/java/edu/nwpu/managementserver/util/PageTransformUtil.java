package edu.nwpu.managementserver.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.nwpu.managementserver.domain.Police;
import edu.nwpu.managementserver.dto.PagingQueryForPrisonAdminParam;
import edu.nwpu.managementserver.dto.PagingQueryParam;
import edu.nwpu.managementserver.vo.PageResult;
import edu.nwpu.managementserver.vo.PoliceVO;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

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
                (int)tPageInfo.getTotal(),
                tList.stream().map(mapper).toList()
        );
    }

    public static <T, R> PageResult<R> toViewPage(PagingQueryForPrisonAdminParam param,
                                                  Function<PagingQueryForPrisonAdminParam, List<T>> query,
                                                  Function<T, R> mapper) {

        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<T> tList = query.apply(param);
        PageInfo<T> tPageInfo = new PageInfo<>(tList);
        return new PageResult<>(
                (int)tPageInfo.getTotal(),
                tList.stream().map(mapper).toList()
        );
    }

    public static <T, R> PageResult<R> toViewPage(PagingQueryParam param,
                                                  Supplier<List<T>> supplier,
                                                  Function<T, R> mapper) {

        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<T> tList = supplier.get();
        PageInfo<T> tPageInfo = new PageInfo<>(tList);
        return new PageResult<>(
                (int)tPageInfo.getTotal(),
                tList.stream().map(mapper).toList()
        );
    }



}
