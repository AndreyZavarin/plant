package com.demo.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer


@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfiguration @Autowired constructor(val authenticationManager: AuthenticationManager) : AuthorizationServerConfigurerAdapter() {



}