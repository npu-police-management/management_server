package com.nwpu.managementserver.service.impl;

import com.nwpu.managementserver.domain.Account;
import com.nwpu.managementserver.dto.AccountUserDetails;
import com.nwpu.managementserver.mapper.AccountMapper;
import com.nwpu.managementserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountMapper.selectOneByAccountNumber(username);
        return AccountUserDetails.of(account);
    }
}
