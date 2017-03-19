package com.demo.services.auth

import com.demo.models.AppUser
import com.demo.services.AppUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
open class CurrentUserDetailsServiceImpl
@Autowired constructor(private val appUserService: AppUserService) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val findUserByLogin = appUserService.findUserByLogin(login)
        return CurrentUser(findUserByLogin);
    }

}

class CurrentUser(val appUser: AppUser) : User(appUser.login, appUser.passwordHash, appUser.role.getAuthorities())