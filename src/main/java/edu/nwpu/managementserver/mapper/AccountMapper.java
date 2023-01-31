package edu.nwpu.managementserver.mapper;

import edu.nwpu.managementserver.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Faust
* @description 针对表【account】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.Account
*/
@Mapper
public interface AccountMapper {

    Account getByAccountNumber(String accountNumber);

    int insert(Account account);

    Account getById(Long id);

    int deleteByPrisonAdminId(List<Long> idList);

    int deleteByPoliceIdList(List<Long> idList);

    int deleteByPrisonId(List<Long> idList);

    int updatePassword(Account account);
}




