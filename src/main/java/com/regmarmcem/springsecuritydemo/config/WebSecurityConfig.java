package com.regmarmcem.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
            .requestMatchers("/hello", "/login").permitAll()
            .requestMatchers("/user").hasAuthority("GENERAL")
            .requestMatchers("/sys_admin").hasAuthority("SYS_ADMIN")
            .requestMatchers("/user_admin").hasAuthority("USER_ADMIN")
            .anyRequest().authenticated()
        ).formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .permitAll()
        ).logout(logout -> logout
            .logoutSuccessUrl("/login")
        );
        
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
