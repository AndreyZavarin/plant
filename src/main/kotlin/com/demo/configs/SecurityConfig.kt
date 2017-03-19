package com.demo.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true) //prePostEnabled = true
//open class SecurityConfig @Autowired constructor(userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity?) {
//        http
//                ?.headers()?.frameOptions()?.sameOrigin()
//                ?.and()
//                ?.addFilterAfter(null, UsernamePasswordAuthenticationFilter::class.java)
//                ?.authorizeRequests()
//                ?.antMatchers()
//    }
//
//    @Bean(name = "restTokenAuthenticationFilter")
//    fun restTokenAuthenticationFilter(): RestTokenAuthenticationFilter {
//        val restTokenAuthenticationFilter = RestTokenAuthenticationFilter()
//        tokenAuthenticationManager.setUserDetailsService(userDetailsService)
//        restTokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager)
//        return restTokenAuthenticationFilter
//    }
//}