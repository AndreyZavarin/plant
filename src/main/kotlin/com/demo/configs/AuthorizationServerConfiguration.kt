package com.demo.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter


@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfiguration @Autowired constructor(val authenticationManager: AuthenticationManager) : AuthorizationServerConfigurerAdapter() {

    @Bean
    open fun accessTokenConverter(): JwtAccessTokenConverter {
        return JwtAccessTokenConverter()
    }

    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer?) {
        oauthServer!!
                .tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
                .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        super.configure(clients) //todo
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }
}