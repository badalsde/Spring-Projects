package com.contact.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig {

    public BCryptPasswordEncoder bCryptPasswordEncoder;
}
