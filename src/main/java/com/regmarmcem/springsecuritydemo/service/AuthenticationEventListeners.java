package com.regmarmcem.springsecuritydemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListeners {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationEventListeners.class);

    @EventListener
    public void handleBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
        log.info("Bad credentials is detected. username: {}", event.getAuthentication().getName());
    }
}
