package com.nwpu.managementserver.mapper;

import com.nwpu.managementserver.domain.Account;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Faust
* @description 针对表【account】的数据库操作Mapper
* @createDate 2023-01-18 12:09:20
* @Entity generator.domain.Account
*/
@Mapper
public interface AccountMapper {

    Account selectOneByAccountNumber(String accountNumber);

    int insert(Account account);
}




