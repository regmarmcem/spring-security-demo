package com.regmarmcem.springsecuritydemo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.regmarmcem.springsecuritydemo.model.AccountUserDetails;
import com.regmarmcem.springsecuritydemo.record.Account;
import com.regmarmcem.springsecuritydemo.repository.AccountRepository;

@Service
public class AccountUserDetailsService implements UserDetailsService {
    
    private final AccountRepository accountRepository;

    
    public AccountUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        return accountOptional.map(account -> new AccountUserDetails(account))
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
