package edu.nwpu.managementserver.service;

import edu.nwpu.managementserver.domain.Account;
import edu.nwpu.managementserver.dto.AccountUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.function.Function;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public interface AccountService extends UserDetailsService {

    AccountUserDetails add(String accountNumber, String password, int role, Function<String, String> encode);

    Account getById(long id);

    void updatePassword(AccountUserDetails userDetails, String newPassword, Function<String, String> encode);
}
