package com.nwpu.managementserver.service;

import com.nwpu.managementserver.domain.Account;
import com.nwpu.managementserver.dto.AccountUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.function.Function;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public interface AccountService extends UserDetailsService {

    AccountUserDetails add(String accountNumber, String password, int role, Function<String, String> encode);

    Account getById(long id);

}
