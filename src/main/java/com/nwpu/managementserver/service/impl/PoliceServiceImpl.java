package com.nwpu.managementserver.service.impl;
import com.nwpu.managementserver.constant.CodeEnum;
import com.nwpu.managementserver.domain.Police;
import com.nwpu.managementserver.exception.BusinessException;
import com.nwpu.managementserver.mapper.AccountMapper;
import com.nwpu.managementserver.mapper.PoliceMapper;
import com.nwpu.managementserver.mapper.PrisonAdminMapper;
import com.nwpu.managementserver.service.PoliceService;
import org.apache.ibatis.binding.BindingException;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nwpu.managementserver.constant.CodeEnum.NotFound;

/**
 * @author GengXuelong
 * @version 1.0
 * @time 2023/1/30 11:35
 * @email 3349495429@qq.com
 * @className com.nwpu.managementserver.service.impl.PoliceServiceImpl
 * @description:
 */
@Service
public class PoliceServiceImpl  implements PoliceService {
    @Autowired
    PoliceMapper policeMapper;
    @Autowired
    PrisonAdminMapper prisonAdminMapper;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public List<Police> queryList(String query, long prison_id) {
        return policeMapper.getPoliceListByNameLike(query,prison_id);
    }

    @Override
    public void add(Police police) {
        policeMapper.save(police);
    }

    @Override
    public long getPrisonIdByAccountId(long id) {
        List<Long> idList = prisonAdminMapper.getPrisonIdByAccountId(id);
        long prison_id =-1;
        if(idList!=null&&idList.size()>0){
            prison_id = idList.get(0);
        }
        return prison_id;
    }

    @Override
    public void deleteList(List<Long> idList) {
        accountMapper.deleteByPoliceIdList(idList);
        policeMapper.deleteByIdList(idList);
    }

    @Override
    public Police getPoliceById(long id) {
        return policeMapper.getById(id);
    }

    @Override
    public void update(Police police) {
        int i = policeMapper.updateById(police);
        if(i<0){
            throw new BusinessException(CodeEnum.ServerError, "数据库错误");
        }
    }

    @Override
    public Police getPoliceByAccountId(Long account_id) {
        return policeMapper.getPoliceByAccountId(account_id);
    }
}
