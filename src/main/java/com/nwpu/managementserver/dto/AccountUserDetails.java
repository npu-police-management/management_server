package com.nwpu.managementserver.dto;

import com.nwpu.managementserver.constant.RoleEnum;
import com.nwpu.managementserver.domain.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountUserDetails implements UserDetails {

    private Long id;

    private String accountNumber;

    private String password;

    private RoleEnum role;

    public static AccountUserDetails of(Account account) {

        return new AccountUserDetails(
                account.getId(),
                account.getAccountNumber(),
                account.getPassword(),
                RoleEnum.fromValue(account.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return accountNumber;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
