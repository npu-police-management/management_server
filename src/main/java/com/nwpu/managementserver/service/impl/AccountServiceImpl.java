package com.nwpu.managementserver.service.impl;

import com.nwpu.managementserver.domain.Account;
import com.nwpu.managementserver.dto.AccountUserDetails;
import com.nwpu.managementserver.exception.BusinessException;
import com.nwpu.managementserver.mapper.AccountMapper;
import com.nwpu.managementserver.service.AccountService;
import com.nwpu.managementserver.util.SnowflakeIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.nwpu.managementserver.constant.CodeEnum.CreationError;
import static com.nwpu.managementserver.constant.CodeEnum.NotFound;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Service
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {

        this.accountMapper = accountMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws BusinessException {

        Account account = accountMapper.getByAccountNumber(username);
        if (account == null) {
            throw new BusinessException(NotFound, "找不到帐户");
        }
        return AccountUserDetails.of(account);
    }

    @Override
    public AccountUserDetails add(String accountNumber, String password, int role, Function<String, String> encode) {

        Account oldAccount = accountMapper.getByAccountNumber(accountNumber);
        if (oldAccount != null) {
            throw new BusinessException(CreationError, "此账户名已存在");
        }
        long id = SnowflakeIdUtil.nextId();
        Account account = new Account(id, accountNumber, encode.apply(password), role);
        accountMapper.insert(account);
        return AccountUserDetails.of(account);
    }

    @Override
    public Account getById(long id) {

        Account account = accountMapper.getById(id);
        if (account == null) {
            throw new BusinessException(NotFound, "找不到该账户");
        }
        return account;
    }
}
