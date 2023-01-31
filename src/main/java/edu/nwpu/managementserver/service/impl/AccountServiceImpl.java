package edu.nwpu.managementserver.service.impl;

import edu.nwpu.managementserver.domain.Account;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import edu.nwpu.managementserver.dto.PasswordChangeParam;
import edu.nwpu.managementserver.exception.BusinessException;
import edu.nwpu.managementserver.mapper.AccountMapper;
import edu.nwpu.managementserver.service.AccountService;
import edu.nwpu.managementserver.util.SnowflakeIdUtil;
import edu.nwpu.managementserver.constant.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;

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
            throw new BusinessException(CodeEnum.NotFound, "找不到帐户");
        }
        return AccountUserDetails.of(account);
    }

    @Override
    public AccountUserDetails add(String accountNumber, String password, int role, Function<String, String> encode) {

        Account oldAccount = accountMapper.getByAccountNumber(accountNumber);
        if (oldAccount != null) {
            throw new BusinessException(CodeEnum.CreationError, "此账户名已存在");
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
            throw new BusinessException(CodeEnum.NotFound, "找不到该账户");
        }
        return account;
    }

    @Override
    public void updatePassword(AccountUserDetails userDetails, PasswordChangeParam param, Function<String, String> encode) {

        if (!userDetails.getPassword().equals(encode.apply(param.getOldPassword()))) {
            throw new BusinessException(CodeEnum.RequestError, "旧密码错误");
        }
        userDetails.setPassword(encode.apply(param.getNewPassword()));
        accountMapper.updatePassword(userDetails.toAccount());
    }
}
