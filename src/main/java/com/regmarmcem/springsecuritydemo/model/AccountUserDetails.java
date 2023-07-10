package com.regmarmcem.springsecuritydemo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.regmarmcem.springsecuritydemo.record.Account;

public class AccountUserDetails implements UserDetails {

    private final Account account;
    private final Collection<? extends GrantedAuthority> authorities;
    
    public AccountUserDetails(Account account) {
        this.account = account;
        this.authorities = account.roleList()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role))
            .toList();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.password();
    }

    @Override
    public String getUsername() {
        return account.email();
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

    public Account getAccount() {
        return account;
    }
}
